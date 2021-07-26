package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.util.CommonUtil;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.mapper.CompanyMapper;
import com.laotan.net.entity.Company;
import com.laotan.net.service.CompImgService;
import com.laotan.net.service.CompWelfareService;
import com.laotan.net.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    @Autowired
    private CompImgService compImgService;
    @Autowired
    private CompWelfareService compWelfareService;

    @Override
    public Integer deleteByCompId(Integer compId) {
        return companyMapper.deleteById(compId);
    }

    @Override
    public Company updateCompanyInfo(Company company) {
        // TODO: 2021/7/13 修改boss信息时连带修改公司信息
        return null;
    }

    @Override
    public IPage<Company> selectCompanyPage(Page page, String compName) {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper();
        if(!StringUtils.isEmpty(compName)){
            queryWrapper.like(Company :: getCompFullName,compName);
        }
        queryWrapper.eq(Company::getStatus,1);
        return companyMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Company selectBycompName(String compName) {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Company::getCompFullName,compName);
        queryWrapper.eq(Company::getStatus,1);
        return companyMapper.selectOne(queryWrapper);
    }

    @Override
    public Company saveOrUpdateCompInfo(Company company) throws Exception{
        if(company.getId() != null && company.getId() != 0){
            //更新
            Company companyDB = super.getById(company.getId());
            Object result = CommonUtil.updateBean(companyDB, company);
            ObjectMapper objectMapper = new ObjectMapper();
            companyDB = objectMapper.convertValue(result, Company.class);
            super.updateById(companyDB);
        }else{
            //新增
            super.save(company);
        }
        //处理外部信息，没有在company表中的：imgList、comWelfareList
        List<CompImg> imgList = company.getImgList();
        if(imgList != null && imgList.size() > 0){
            for (CompImg imgInfo:imgList) {
                imgInfo.setCompId(company.getId());
                // TODO: 2021/7/26 图片路径怎么搞？
            }
            compImgService.saveBatch(imgList);
        }
        List<CompWelfare> comWelfareList = company.getComWelfareList();
        if(comWelfareList != null && comWelfareList.size() > 0){
            for (CompWelfare compWelfare:comWelfareList) {
                compWelfare.setCompId(company.getId());
            }
            compWelfareService.saveBatch(comWelfareList);
        }
        return company;
    }

    @Override
    public Company selectBycompId(Integer compId) {
        Company company = super.getById(compId);
        List<CompImg> imgList = compImgService.selectByCompId(compId);
        company.setImgList(imgList);
        List<CompWelfare> comWelfareList = compWelfareService.selectByCompId(compId);
        company.setComWelfareList(comWelfareList);
        return company;
    }
}
