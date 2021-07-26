package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.util.DESUtil;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.UserToken;
import com.laotan.net.mapper.UserTokenMapper;
import com.laotan.net.service.AccountService;
import com.laotan.net.service.SystemParamService;
import com.laotan.net.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

    Logger logger = LoggerFactory.getLogger(UserTokenServiceImpl.class);

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private AccountService accountService;

    @Override
    public UserToken getUserTokenByAccount(String account) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("ACCOUNT",account);
        List<UserToken> list = userTokenMapper.selectList(wrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public String createUserToken(Account account) {
        //1、查询此工号是否已经存在有效token，若存在直接返回
        UserToken userToken = this.getUserTokenByAccount(account.getCellPhone());
        String time = systemParamService.selectByParamKey("TOKEN_LIVE_TIME","");
        if(userToken != null){
            LocalDateTime failureTime = userToken.getFailureTime();
            if(failureTime != null && failureTime.isAfter(LocalDateTime.now())){
                //token未过期：token为key,userCode为value，存入redis失效间隔时间，单位为秒
                int liveSecond = StringUtils.isEmpty(time) ? 60*60*24 :Integer.parseInt(time);
                Long failureSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + liveSecond;
                userToken.setFailureTime(LocalDateTime.ofEpochSecond(failureSecond,0, ZoneOffset.ofHours(8)));
                account.setToken(userToken.getClientToken());
                return userToken.getClientToken();
            }
        }else{
            userToken = new UserToken();
        }
        //2、生成新的clientToken
        String salt = UUID.randomUUID().toString().replace("-","");
        String encrypt = "";
        try {
            encrypt = DESUtil.encrypt(account.getId() + "", salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、保存或更新userToken对象
        userToken.setClientToken(encrypt);
        userToken.setSalt(salt);
        userToken.setAccount(account.getCellPhone());
        //获取token生命周期，单位为秒,如果没有设置，默认为一天，24小时
        //token为key,userCode为value，存入redis失效间隔时间，单位为秒
        int liveSecond = StringUtils.isEmpty(time) ? 60*60*24 :Integer.parseInt(time);
//        try{
//            RedisUtils.set(encrypt,userToken.getAccount(),liveSecond);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Long failureSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + liveSecond;
        userToken.setFailureTime(LocalDateTime.ofEpochSecond(failureSecond,0, ZoneOffset.ofHours(8)));

        if(userToken.getId() == null){
            userToken.setCreateTime(LocalDateTime.now());
            userToken.setUpdateTime(LocalDateTime.now());
        }else{
            userToken.setUpdateTime(LocalDateTime.now());
        }
        this.saveOrUpdate(userToken);
        account.setToken(encrypt);
        return encrypt ;
    }

    @Override
    public UserToken getByToken(String clientToken) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("CLIENT_TOKEN",clientToken);
        UserToken userToken = userTokenMapper.selectOne(wrapper);
        return userToken;
    }

    @Override
    public UserToken refreshToken(String token) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("client_token",token);
        UserToken userToken = userTokenMapper.selectOne(wrapper);
        if(userToken == null){
            return null;
        }
        //重新生成token保存redis和更新数据库失效时间字段
        String cellPhone = userToken.getAccount();
        Account account = accountService.selectByCellPhone(cellPhone);
        String newClientToken = this.createUserToken(account);
        userToken.setClientToken(newClientToken);

        //获取token生命周期，单位为秒,如果没有设置，默认为一天，24小时
        String time = systemParamService.selectByParamKey("TOKEN_LIVE_TIME","");
        //token为key,userCode为value，存入redis失效间隔时间，单位为秒
        int liveSecond = StringUtils.isEmpty(time) ? 60*60*24 :Integer.parseInt(time);
//        RedisUtils.set(newClientToken,userToken.getAccount(),liveSecond);
        Long failureSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + liveSecond;
        userToken.setFailureTime(LocalDateTime.ofEpochSecond(failureSecond,0, ZoneOffset.ofHours(8)));

//      更新userToken中的token失效时间和新的token
        super.updateById(userToken);
        return userToken;
    }
    @Override
    public JsonResult authToken(String clientToken) {
        if(StringUtils.isEmpty(clientToken)){
            logger.error("验证token方法authToken：参数为空");
            return new JsonResult(ResultStatusCode.TOKEN_ERROR.getCode(),"token不能为空");
        }
        UserToken userToken = this.getByToken(clientToken);
        if(userToken == null){
            logger.error("验证token方法authToken：userToken不存在");
            return new JsonResult(ResultStatusCode.TOKEN_ERROR.getCode(),"token不存在");
        }
        LocalDateTime failureTime = userToken.getFailureTime();
        if(failureTime != null && failureTime.isBefore(LocalDateTime.now())){
            logger.error("验证token方法authToken：token已失效");
            return new JsonResult(ResultStatusCode.TOKEN_ERROR.getCode(),"token已失效");
        }
        //1、解密token，对比account，是否为同一用户唯一token
        String decryptToken = "";//token解密结果
        try {
            decryptToken = DESUtil.decrypt(clientToken, userToken.getSalt());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Account account = new Account();
        try {
            account = accountService.getById(Integer.parseInt(decryptToken));
            if(account == null){
                return new JsonResult(ResultStatusCode.TOKEN_ERROR.getCode(),"非法token");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(ResultStatusCode.TOKEN_ERROR.getCode(),"非法token");
        }

        //2、验证token：redis是否存在且没过期？如果redis没有，检查数据库是否存在且没过期，如果token有效，自动延长失效时间
        //获取token生命周期，单位为秒,如果没有设置，默认为一天，24小时
        String time = systemParamService.selectByParamKey("TOKEN_LIVE_TIME","");
        //token为key,userId为value，存入redis失效间隔时间，单位为秒
        int liveSecond = StringUtils.isEmpty(time) ? 60*60*24 :Integer.parseInt(time);
        try{
//            boolean exists = RedisUtils.exists(clientToken);
//            if(!exists){
                if(userToken.getFailureTime().isAfter(LocalDateTime.now()) ){
                    logger.error("验证token方法authToken：token已过期");
                    return new JsonResult(ResultStatusCode.FAIL_OPERATION.getCode(),"token已过期");
                }
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //此token信息有效，且没有过期，自动延长失效时间
        Long failureSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + liveSecond;
        userToken.setFailureTime(LocalDateTime.ofEpochSecond(failureSecond,0, ZoneOffset.ofHours(8)));
        this.updateById(userToken);
        return new JsonResult(ResultStatusCode.SUCCESS,account);
    }
}
