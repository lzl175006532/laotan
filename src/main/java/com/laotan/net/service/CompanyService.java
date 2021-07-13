package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
}
