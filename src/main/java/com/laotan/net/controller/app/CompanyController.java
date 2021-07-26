package com.laotan.net.controller.app;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.Company;
import com.laotan.net.service.BossService;
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
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value="保存或更新企业信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可", notes="保存或更新企业信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可")
    @PostMapping(value = "/saveOrUpdateInfo")
    public JsonResult saveOrUpdateInfo(@RequestBody Company company) {
        if(company == null){
            return new JsonResult(ResultStatusCode.NOT_NULL);
        }
        logger.info("保存或更新企业信息，企业名称为{}",company.getCompFullName());
        try {
            company = companyService.saveOrUpdateCompInfo(company);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultStatusCode.FAIL_OPERATION);
        }
        return new JsonResult(ResultStatusCode.SUCCESS,company);
    }

}
