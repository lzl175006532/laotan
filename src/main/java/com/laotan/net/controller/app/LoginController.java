package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.User;
import com.laotan.net.service.AccountService;
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


}
