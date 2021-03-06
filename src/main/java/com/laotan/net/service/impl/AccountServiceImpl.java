package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.common.util.MD5Util;
import com.laotan.net.common.util.RedisUtils;
import com.laotan.net.common.util.TokenUtil;
import com.laotan.net.mapper.AccountMapper;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    public Account findByCellPhone(String cellPhone) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Account :: getCellPhone,cellPhone);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public JsonResult login(String cellPhone , String verifyCode, String password, String type) {
        Account accountDB = this.findByCellPhone(cellPhone);
        if("PASSWORD".equals(type)){
            if(accountDB == null){
                return new JsonResult(ResultStatusCode.ACCOUNT_USER_NOT_EXIST);
            }
            //密码方式登录,校验密码
            String salt = accountDB.getSalt();
            String md5Str = MD5Util.getMD5Str(password+salt);
            if(accountDB != null && !accountDB.getPassword().equals(md5Str)){
                return new JsonResult(ResultStatusCode.PASSWORD_ERROR);
            }
        }else{
            //短信验证码方式
            Object cacheObject = redisUtils.getCacheObject(cellPhone);
            if(cacheObject == null || !verifyCode.equals(cacheObject.toString())){
                return new JsonResult(ResultStatusCode.INVALID_CAPTCHA);
            }
            if(accountDB == null){
                //新用户，需要注册
                accountDB = new Account();
                accountDB.setCellPhone(cellPhone);
                accountDB.setCreateTime(LocalDateTime.now());
                super.save(accountDB);
            }
        }
        //增加token逻辑
        userTokenService.createUserToken(accountDB);

        boolean b = this.updateById(accountDB);
        if(!b){
            return new JsonResult(ResultStatusCode.FAIL_OPERATION.getCode(),"保存token失败");
        }
        return new JsonResult(ResultStatusCode.SUCCESS,accountDB);
    }

    @Override
    public Account setPassword(String cellPhone, String password) {
        Account accountDB = this.findByCellPhone(cellPhone);
        if(accountDB == null){
            return null;
        }
        //第一次设置密码，需要把用户输入新密码设置进去，后续进行md5加密
        accountDB.setPassword(password);
        accountDB.setUpdateTime(LocalDateTime.now());
        //设置密码
        accountDB = MD5Util.getMD5Str2Account(accountDB);
        super.saveOrUpdate(accountDB);
        return accountDB;
    }

    @Override
    public Account selectByCellPhone(String cellPhone) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Account::getCellPhone,cellPhone);
        Account account = accountMapper.selectOne(queryWrapper);
        return account;
    }

    @Override
    public Account selectByToken(String token) {
        Integer id = TokenUtil.getAccoutIdByToken(token);
        return super.getById(id);
    }
}
