package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.Company;
import com.laotan.net.service.CompImgService;
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
 * @Description: 公司注册信息
 */
@RestController
@RequestMapping("app/company")
@Api(tags = {"公司注册信息"})
public class CompanyController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompImgService compImgService;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 16:32
     * @Params: [boss]
     * @Return: com.laotan.net.common.JsonResult
     * @Description: 根据企业名称（营业执照上的全称）查询数据库是否已有人注册为此
     */
    @ApiOperation(value="根据企业全称查询是否已经在工商信息中注册,如果已注册返回true，未注册返回false", notes="根据企业全称查询是否已经在工商信息中注册,如果已注册返回true，未注册返回false")
    @PostMapping(value = "/selectBycompName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compName", value = "公司全称（营业执照上的全称）", dataType = "string", required = true, defaultValue = "")
    })
    public JsonResult selectBycompName(String compName) {
        if(StringUtils.isEmpty(compName)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("根据企业全称查询是否已经在工商信息中注册，公司名称为{}",compName);
        Company company = companyService.selectBycompName(compName);
        if(company == null){
            return new JsonResult(ResultStatusCode.SUCCESS,false);
        }else{
            return new JsonResult(ResultStatusCode.FAIL_OPERATION,true);
        }

    }

    @ApiOperation(value="根据id查询公司详情信息", notes="根据id查询公司详情信息")
    @PostMapping(value = "/selectBycompId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "公司id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult selectBycompId(Integer compId) {
        if(compId == null || compId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("根据id查询公司详情信息，公司id为{}",compId);
        Company company = companyService.selectBycompId(compId);
        if(company == null){
            return new JsonResult(ResultStatusCode.DB_RESOURCE_NULL);
        }
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更公司基本信息
     */
    @ApiOperation(value="保存或更公司基本信息", notes="保存或更公司基本信息")
    @PostMapping(value = "/saveOrUpdateJBXXInfo")
    public JsonResult saveOrUpdateJBXXInfo( Company company) {
        if(company == null || company.getBossId() == null || company.getBossId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更公司基本信息");
        company = companyService.saveOrUpdateCompInfo(company);
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存公司企业福利
     */
    @ApiOperation(value="保存公司企业福利", notes="保存公司企业福利")
    @PostMapping(value = "/saveOrUpdateQYFLInfo")
    public JsonResult saveOrUpdateQYFLInfo( Company company) {
        if(company == null || company.getBossId() == null || company.getBossId() == 0
                || company.getComWelfareList() == null || company.getComWelfareList().size() <= 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存公司企业福利");
        company = companyService.saveOrUpdateQYFLInfo(company);
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新公司企业介绍
     */
    @ApiOperation(value="保存或更新公司企业介绍", notes="保存或更新公司企业介绍")
    @PostMapping(value = "/saveOrUpdateQYJSInfo")
    public JsonResult saveOrUpdateQYJSInfo( Company company) {
        if(company == null || company.getBossId() == null || company.getBossId() == 0
                || StringUtils.isEmpty(company.getCompDetail())){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更新公司企业介绍");
        company = companyService.saveOrUpdateQYJSInfo(company);
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存公司企业相册
     */
    @ApiOperation(value="保存公司企业相册", notes="保存公司企业相册")
    @PostMapping(value = "/saveOrUpdateQYXCInfo")
    public JsonResult saveOrUpdateQYXCInfo( CompImg compImg) {
        if(compImg == null || compImg.getCompId() == null || compImg.getCompId() == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存公司企业相册");
        compImg = companyService.saveOrUpdateQYXCInfo(compImg);
        return new JsonResult(ResultStatusCode.SUCCESS,compImg);
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 删除公司企业相册
     */
    @ApiOperation(value="删除公司企业相册", notes="删除公司企业相册")
    @PostMapping(value = "/deleteJYJLInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imgId", value = "公司相册图片id", dataType = "int", required = true, defaultValue = "")
    })
    public JsonResult deleteJYJLInfo(Integer imgId) {
        if(imgId == null || imgId == 0){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("删除公司企业相册");
        compImgService.deleteById(imgId);
        return new JsonResult(ResultStatusCode.SUCCESS);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新公司企业官网
     */
    @ApiOperation(value="保存或更新公司企业官网", notes="保存或更新公司企业官网")
    @PostMapping(value = "/saveOrUpdateQYGWInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "公司id", dataType = "int", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "compUccn", value = "公司官网", dataType = "string", required = true, defaultValue = "")
    })
    public JsonResult saveOrUpdateQYGWInfo( Integer compId,String compUccn) {
        if(compId == null || compId == 0 || StringUtils.isEmpty(compUccn)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更新公司企业官网");
        compUccn = companyService.saveOrUpdateQYGWInfo(compId,compUccn);
        return new JsonResult(ResultStatusCode.SUCCESS,compUccn);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新公司企业地址
     */
    @ApiOperation(value="保存或更新公司企业地址", notes="保存或更新公司企业地址")
    @PostMapping(value = "/saveOrUpdateQYDZInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "公司id", dataType = "int", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "compUrl", value = "企业地址", dataType = "string", required = true, defaultValue = "")
    })
    public JsonResult saveOrUpdateQYDZInfo( Integer compId,String compUrl) {
        if(compId == null || compId == 0 || StringUtils.isEmpty(compUrl)){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更新公司企业地址");
        compUrl = companyService.saveOrUpdateQYDZInfo(compId,compUrl);
        return new JsonResult(ResultStatusCode.SUCCESS,compUrl);
    }
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:38
     * @Params: [account, password]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 保存或更新公司工商信息
     */
    @ApiOperation(value="保存或更新公司工商信息", notes="保存或更新公司工商信息")
    @PostMapping(value = "/saveOrUpdateGSXXInfo")
    public JsonResult saveOrUpdateGSXXInfo( Company company) {
        if(company == null || company.getId() == 0 || company.getId() == null ){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更新公司工商信息");
        company = companyService.saveOrUpdateGSXXInfo(company);
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }
}
