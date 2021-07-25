package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.User;
import com.laotan.net.service.AccountService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 17:56
 * @Description: 前端应聘者用户信息
 */
@RestController
@RequestMapping("app/user")
@Api(tags = {"前端应聘者用户信息"})
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新用户注册简历信息
     */
    @ApiOperation(value="保存或更新用户注册简历信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可", notes="保存或更新用户注册简历信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可")
    @PostMapping(value = "/saveOrUpdateInfo")
    public JsonResult saveOrUpdateInfo(@RequestBody User user) {
        if(user == null || StringUtils.isEmpty(user.getLoginCellPhone())){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        String cellPhone = user.getLoginCellPhone();
        Account account = accountService.selectByCellPhone(cellPhone);
        if(account == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL.getCode(),"数据库中没有"+cellPhone+"账号信息");
        }
        logger.info("保存或更新用户注册简历信息，姓名为{}",user.getUsername());
        User saveUserDB = userService.saveOrUpdateInfo(user);
        return new JsonResult(ResultStatusCode.SUCCESS,saveUserDB);
    }

    @ApiOperation(value="根据token获取用户信息", notes="根据token获取用户信息")
    @PostMapping(value = "/selectUserInfoByCellPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cellPhone", value = "手机号", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "loginType", value = "USER-应聘者登录，BOSS-boss登录", dataType = "String", required = true, defaultValue = "")
    })
    public JsonResult selectUserInfoByToken(String token) throws Exception {
        logger.info("根据token:{}获取用户信息",token);
        if(StringUtils.isEmpty(token)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        User userDB = userService.selectUserInfoByToken(token);
        if(userDB == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL.getCode(),"token无效或数据库无资源");
        }
        return new JsonResult(ResultStatusCode.SUCCESS,userDB);
    }

}
