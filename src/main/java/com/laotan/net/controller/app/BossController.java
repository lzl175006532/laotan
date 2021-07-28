package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.Company;
import com.laotan.net.service.BossService;
import com.laotan.net.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
 * @Description: 前端招聘者用户信息
 */
@RestController
@RequestMapping("app/boss")
@Api(tags = {"前端招聘者用户信息"})
public class BossController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BossService bossService;
    @Autowired
    private CompanyService companyService;

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
    public JsonResult saveOrUpdateInfo(Boss boss) {
        if(boss == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("注册招聘者用户信息，姓名为{}",boss.getUsername());
        Boss bossSave = bossService.saveOrUpdateBossInfo(boss);
        return new JsonResult(ResultStatusCode.SUCCESS,bossSave);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 16:32
     * @Params: [boss]
     * @Return: com.laotan.net.common.JsonResult
     * @Description: HR加入到现有公司中
     */
    @ApiOperation(value="HR加入到现有公司中", notes="HR加入到现有公司中")
    @PostMapping(value = "/joinCompany")
    public JsonResult joinCompany(@RequestBody Boss boss) {
        if(boss == null || StringUtils.isEmpty(boss.getCompanyName())){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("HR{}加入到现有公司{}中",boss.getUsername(),boss.getCompanyName());
        Company company = companyService.selectBycompName(boss.getCompanyName());
        boss.setCompId(company.getId());
        return this.saveOrUpdateInfo(boss);
    }

}
