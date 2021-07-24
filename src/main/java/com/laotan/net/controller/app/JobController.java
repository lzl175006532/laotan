package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.Job;
import com.laotan.net.service.BossService;
import com.laotan.net.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("app/job")
@Api(tags = {"前端职位控制层"})
public class JobController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobService jobService;


    @ApiOperation(value="前端职位列表", notes="前端职位列表")
    @PostMapping(value = "/selectByParentId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级id", dataType = "int", required = false, defaultValue = "0")
    })
    public JsonResult selectByParentId( String parentId ) {
        if(parentId == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        List<Job> jobList = jobService.selectByParentId(parentId);
        return new JsonResult(ResultStatusCode.SUCCESS,jobList);
    }

}
