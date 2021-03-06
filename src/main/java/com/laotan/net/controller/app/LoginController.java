package com.laotan.net.controller.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.common.util.SendMsgUtil;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import com.laotan.net.vo.SearchJobVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 17:56
 * @Description: app账号密码登录
 */
@RestController
@RequestMapping("app/login")
@Api(tags = {"前端手机号登录"})
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private SendMsgUtil sendMsgUtil;
    @Autowired
    private PublishJobService publishJobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserTokenService userTokenService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: app手机号、短信验证码登录
     */
    @ApiOperation(value="手机号发送验证码", notes="手机号发送验证码")
    @PostMapping(value = "/sendMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult sendMsg(String cellPhone){
        logger.info("手机号{}开始发送验证码",cellPhone);
        if(StringUtils.isEmpty(cellPhone) ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        return sendMsgUtil.sendVerificationCode(cellPhone);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: app手机号、短信验证码登录
     */
    @ApiOperation(value="前端手机号、短信验证码登录", notes="前端手机号、短信验证码登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "verifyCode", value = "短信验证码", dataType = "String", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "type", value = "登录类型（PASSWORD-手机号密码登录，VERIFYCODE-手机号验证码登录）", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult login(String cellPhone , String verifyCode,String password,String type){
        logger.info("手机号{}开始登录，登录方式为{}",cellPhone,type);
        if(StringUtils.isEmpty(cellPhone) || StringUtils.isEmpty(type) || (StringUtils.isEmpty(password) && StringUtils.isEmpty(verifyCode))){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        if(!"PASSWORD".equals(type) && !"VERIFYCODE".equals(type)){
            return new JsonResult(ResultStatusCode.DONT_MESS_ABOUT);
        }

        return accountService.login(cellPhone, verifyCode, password, type);
    }

    @ApiOperation(value="手机验证码登录注册之后设置密码", notes="手机验证码登录注册之后设置密码")
    @PostMapping(value = "/setPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult setPassword(String cellPhone , String password){
        logger.info("手机号{}设置密码",cellPhone);
        if(StringUtils.isEmpty(cellPhone) || StringUtils.isEmpty(password)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        Account account = accountService.setPassword(cellPhone,password);
        if(account == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL);
        }
        logger.info("{}设置密码成功",cellPhone);
        return new JsonResult(ResultStatusCode.SUCCESS,account);
    }

    @ApiOperation(value="登录成功-进入首页：根据登录用户信息返回岗位列表", notes="登录成功-进入首页：选择身份（招聘者还是应聘者）返回岗位列表")
    @PostMapping(value = "/checkIdentity")
    public JsonResult checkIdentity(@RequestBody SearchJobVO searchJobVO){
        if(searchJobVO == null || StringUtils.isEmpty(searchJobVO.getCellPhone()) || StringUtils.isEmpty(searchJobVO.getIdentity()) ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("手机号{}选择登录，身份为{}",searchJobVO.getCellPhone(),searchJobVO.getIdentity());
        //如果是招聘者返回首页为职位列表，如果是应聘者返回首页为职位列表
        //搜索条件为公司名称搜索，则返回公司列表信息
        if(StringUtils.isEmpty(searchJobVO.getCompName())){
            //查询公司
            IPage<Company> companyIPage = companyService.selectCompanyPage(searchJobVO.getPage(), searchJobVO.getCompName());
            return new JsonResult(ResultStatusCode.SUCCESS,companyIPage);
        }
        //搜索条件为职位名称搜索，则返回职位列表信息
        IPage<PublishJob> publishJobIPage = publishJobService.selectJobByCellPhone(searchJobVO);
        return new JsonResult(ResultStatusCode.SUCCESS,publishJobIPage);
    }


}
