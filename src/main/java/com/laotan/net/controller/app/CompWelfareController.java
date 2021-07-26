package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.service.CompWelfareService;
import com.laotan.net.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CompWelfareController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompWelfareService compWelfareService;
    @Autowired
    private CompanyService companyService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 为公司新增福利
     */
    @ApiOperation(value="为公司新增福利", notes="为公司新增福利")
    @PostMapping(value = "/addForCompany")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "welfareName", value = "福利名称", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "compId", value = "公司id", dataType = "int", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "type", value = "福利类型：自定义-CUSTOM，默认-DEFAULT", dataType = "String", required = true, defaultValue = "CUSTOM")
    })
    public JsonResult addForCompany(String welfareName,Integer compId,String type) {
        if(StringUtils.isEmpty(welfareName) || compId == null || compId == 0 || StringUtils.isEmpty(type) ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        if(!"CUSTOM".equals(type) && !"DEFAULT".equals(type)){
            return new JsonResult(ResultStatusCode.DONT_MESS_ABOUT);
        }
        logger.info("新增公司福利：{}",welfareName);
        CompWelfare compWelfare = compWelfareService.addForCompany(welfareName,compId,type);
        return new JsonResult(ResultStatusCode.SUCCESS,compWelfare);
    }


    @ApiOperation(value="为公司删除福利", notes="为公司删除福利")
    @PostMapping(value = "/delForCompany")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "welfareName", value = "福利名称", dataType = "String", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "compId", value = "公司id", dataType = "int", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "type", value = "福利类型：自定义-CUSTOM，DEFAULT-默认", dataType = "String", required = true, defaultValue = "CUSTOM")
    })
    public JsonResult delForCompany(String welfareName,Integer compId,String type) {
        if(StringUtils.isEmpty(welfareName) || compId == null || compId == 0 || StringUtils.isEmpty(type) ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        if(!"CUSTOM".equals(type) && !"DEFAULT".equals(type)){
            return new JsonResult(ResultStatusCode.DONT_MESS_ABOUT);
        }
        logger.info("删除公司福利：{}",welfareName);
        compWelfareService.delForCompany(welfareName,compId,type);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }

}
