package com.laotan.net.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.common.JsonResult;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.Company;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface CompanyService extends IService<Company> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:05
     * @Params: [id]
     * @Return: java.lang.Integer
     * @Description: 根据用户id删除
     */
    Integer deleteByCompId(Integer compId);


    Company updateCompanyInfo(Company company);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/23 17:47
     * @Params: [page, compName]
     * @Return: com.laotan.net.common.JsonResult
     * @Description: 根据名称模糊查询公司信息
     */
    IPage<Company> selectCompanyPage(Page page, String compName);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 17:48
     * @Params: [compName]
     * @Return: com.laotan.net.entity.Company
     * @Description: 根据公司名称查询是否已在工商信息中注册
     */
    Company selectBycompName(String compName);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 18:00
     * @Params: [company]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存或更新企业基本信息
     */
    Company saveOrUpdateCompInfo(Company company);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 根据公司id查询公司详情
     */
    Company selectBycompId(Integer compId);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存或修改公司企业福利
     */
    Company saveOrUpdateQYFLInfo(Company company);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存或修改公司企业介绍
     */
    Company saveOrUpdateQYJSInfo(Company company);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存公司企业相册
     */
    CompImg saveOrUpdateQYXCInfo(CompImg company);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存公司企业官网
     */
    String saveOrUpdateQYGWInfo(Integer compId,String compUccn);
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存公司企业地址
     */
    String saveOrUpdateQYDZInfo(Integer compId, String compUrl);
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 19:59
     * @Params: [compId]
     * @Return: com.laotan.net.entity.Company
     * @Description: 保存公司企业地址
     */
    Company saveOrUpdateGSXXInfo(Company company);
}
