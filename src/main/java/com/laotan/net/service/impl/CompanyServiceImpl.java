package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.CompImgMapper;
import com.laotan.net.controller.mapper.CompanyMapper;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.Company;
import com.laotan.net.service.CompImgService;
import com.laotan.net.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Integer deleteByCompId(Integer compId) {
        return companyMapper.deleteById(compId);
    }

    @Override
    public Company updateCompanyInfo(Company company) {
        // TODO: 2021/7/13 修改boss信息时连带修改公司信息
        return null;
    }
}
