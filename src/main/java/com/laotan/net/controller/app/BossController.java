package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.User;
import com.laotan.net.service.BossService;
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
 * @Description: 前端招聘者用户信息
 */
@RestController
@RequestMapping("app/user")
@Api(tags = {"前端招聘者用户信息"})
public class BossController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BossService bossService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新招聘者用户注册信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可
     */
    @ApiOperation(value="保存或更新招聘者用户注册信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可", notes="保存或更新招聘者用户注册信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可")
    @PostMapping(value = "/saveOrUpdateInfo")
    public JsonResult saveOrUpdateInfo(@RequestBody Boss boss) {
        if(boss == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("注册招聘者用户信息，姓名为{}",boss.getUsername());
        Boss bossSave = bossService.saveOrUpdateInfo(boss);
        return new JsonResult(ResultStatusCode.SUCCESS,bossSave);
    }

}
