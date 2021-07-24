package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Job;
import com.laotan.net.entity.SystemParam;
import com.laotan.net.service.JobService;
import com.laotan.net.service.SystemParamService;
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

import java.util.List;

/**
 * @Copyright: 通泰信诚
 * @Author: lizilong
 * @Since: 2021/5/19 17:56
 * @Description: 前端职位控制层
 */
@RestController
@RequestMapping("app/param")
@Api(tags = {"前端系统参数控制层"})
public class SystemParamController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemParamService systemParamService;


    @ApiOperation(value="系统参数表", notes="系统参数表")
    @PostMapping(value = "/selectByType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = false, defaultValue = "")
    })
    public JsonResult selectByType( String type ) {
        if(StringUtils.isEmpty(type)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        List<SystemParam> systemParams = systemParamService.selectByType(type);
        return new JsonResult(ResultStatusCode.SUCCESS,systemParams);
    }

    @ApiOperation(value="根据系统参数查询参数值", notes="根据系统参数查询参数值")
    @PostMapping(value = "/selectParamValueByParamName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramName", value = "参数名称", dataType = "String", required = false, defaultValue = ""),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", required = false, defaultValue = "")
    })
    public JsonResult selectParamValueByParamName( String paramName,String type ) {
        if(StringUtils.isEmpty(paramName)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        String systemValue = systemParamService.selectByParamKey(paramName,type);
        return new JsonResult(ResultStatusCode.SUCCESS,systemValue);
    }

}
