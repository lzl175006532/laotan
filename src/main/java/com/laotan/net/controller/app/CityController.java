package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.City;
import com.laotan.net.service.CityService;
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
 * @Description: app账号密码登录
 */
@RestController
@RequestMapping("app/city")
@Api(tags = {"前端查询城市控制层"})
public class CityController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityService cityService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 指定层级类型查询（0-中国，1-省级，2-市级，3-县级）
     */
    @ApiOperation(value="指定层级类型查询（0-中国，1-省级，2-市级，3-县级）", notes="指定层级查询（0-中国，1-省级，2-市级，3-县级）")
    @PostMapping(value = "/selectByType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "城市层级类型", dataType = "int", required = false, defaultValue = "0")
    })
    public JsonResult selectByType(Integer type){
        if(StringUtils.isEmpty(type) ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        List<City> cityList = cityService.selectByType(type);
        if(cityList == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL);
        }
        return new JsonResult(ResultStatusCode.SUCCESS,cityList);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 指定城市id查询下一级城市集合
     */
    @ApiOperation(value="指定城市id查询下一级城市集合", notes="指定城市id查询下一级城市集合")
    @PostMapping(value = "/selectNextListById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "城市层级类型", dataType = "int", required = false, defaultValue = "0")
    })
    public JsonResult selectNextListById(Integer parentId){
        if(parentId == null || parentId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        List<City> cityList = cityService.selectNextListById(parentId);
        if(cityList == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL);
        }
        return new JsonResult(ResultStatusCode.SUCCESS,cityList);
    }



}
