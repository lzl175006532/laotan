package com.laotan.net.controller.app;

import com.laotan.net.common.CommenEnum;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.PublishJob;
import com.laotan.net.entity.User;
import com.laotan.net.service.AccountService;
import com.laotan.net.service.PublishJobService;
import com.laotan.net.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    private PublishJobService publishJobService;

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
            @ApiImplicitParam(name = "verifyCode", value = "短信验证码", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult login(String cellPhone , String verifyCode){
        logger.info("手机号{}开始登录，验证码为{}",cellPhone,verifyCode);
        if(StringUtils.isEmpty(cellPhone) || StringUtils.isEmpty(verifyCode)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        // TODO: 2021/7/13 验证手机号验证码逻辑

        logger.info("{}登录结束，验证码为{}",cellPhone,verifyCode);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }
    @ApiOperation(value="选择身份（招聘者还是应聘者）", notes="选择身份（招聘者还是应聘者）")
    @PostMapping(value = "/checkIdentity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "identity", value = "用户身份（USER-应聘者，BOSS-招聘者）", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult checkIdentity(String cellPhone , String identity){
        logger.info("手机号{}选择身份登录，身份为{}",cellPhone,identity);
        if(StringUtils.isEmpty(cellPhone) || StringUtils.isEmpty(identity)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        //如果是招聘者返回首页为职位列表，如果是应聘者返回首页为职位列表
        List<PublishJob> publishJobList = publishJobService.selectJobByCellPhone(cellPhone,identity);
        return new JsonResult(ResultStatusCode.SUCCESS,publishJobList);
    }


}
