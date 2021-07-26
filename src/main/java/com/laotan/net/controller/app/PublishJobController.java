package com.laotan.net.controller.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laotan.net.common.CommenEnum;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.PublishJob;
import com.laotan.net.service.AccountService;
import com.laotan.net.service.PublishJobService;
import com.laotan.net.service.UserService;
import com.laotan.net.vo.SearchJobVO;
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
 * @Description: app前端手机发布职位控制层
 */
@RestController
@RequestMapping("app/login")
@Api(tags = {"前端手机发布职位控制层"})
public class PublishJobController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PublishJobService publishJobService;


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 新增发布职位信息
     */
    @ApiOperation(value="新增发布职位信息", notes="新增发布职位信息")
    @PostMapping(value = "/saveInfo")
    public JsonResult saveInfo(@RequestBody PublishJob publishJob){
        logger.info("新增发布职位信息{}",publishJob);
        if(publishJob == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        publishJobService.saveInfo(publishJob);

        return new JsonResult(ResultStatusCode.SUCCESS);
    }

    @ApiOperation(value="职位信息列表页面", notes="职位信息列表页面")
    @PostMapping(value = "/selectJobList")
    public JsonResult selectJobList(@RequestBody SearchJobVO searchJobVO){
        logger.info("查询职位信息{}",searchJobVO);
        if(searchJobVO == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        IPage<PublishJob> iPage = publishJobService.selectJobList(searchJobVO);

        return new JsonResult(ResultStatusCode.SUCCESS,iPage);
    }

    @ApiOperation(value="职位信息列表页面", notes="职位信息列表页面")
    @PostMapping(value = "/selectJobList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobId", value = "职位id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult selectById(Integer jobId){
        logger.info("查询职位信息详情id={}",jobId);
        if(jobId == null || jobId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        PublishJob publishJob = publishJobService.selectById(jobId);
        if(publishJob == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL);
        }
        return new JsonResult(ResultStatusCode.SUCCESS,publishJob);
    }

}
