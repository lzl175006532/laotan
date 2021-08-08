package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.util.CommonUtil;
import com.laotan.net.common.util.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private FileUtils fileUtils;

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
    public Company saveOrUpdateCompInfo(Company company){

        if(company.getLogoFile() != null){
            //处理logo文件
            MultipartFile logoFile = company.getLogoFile();
            StringBuffer logoFilePath = new StringBuffer();
            String logoName = fileUtils.uploadFile(logoFile, logoFilePath);
            company.setLogoName(logoName);
            company.setLogoPath(logoFilePath.toString());
        }
        super.saveOrUpdate(company);
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

    @Override
    public Company saveOrUpdateQYFLInfo(Company company) {
        List<CompWelfare> comWelfareList = company.getComWelfareList();
        //删除之前公司福利
        compWelfareService.deleteByCompId(company.getId());
        if(comWelfareList !=null && comWelfareList.size() > 0){
            compWelfareService.saveBatch(comWelfareList);
        }
        return company;
    }

    @Override
    public Company saveOrUpdateQYJSInfo(Company company) {
        Integer id = company.getId();
        Company companyDB = super.getById(id);
        companyDB.setCompDetail(company.getCompDetail());
        super.updateById(companyDB);
        return companyDB;
    }

    @Override
    public CompImg saveOrUpdateQYXCInfo(CompImg compImg) {
        StringBuffer filePath = new StringBuffer();
        String fileName = fileUtils.uploadFile(compImg.getImgFile(), filePath);
        compImg.setUrl(filePath.toString());
        compImg.setImgName(fileName);
        compImgService.save(compImg);
        return compImg;
    }

    @Override
    public String saveOrUpdateQYGWInfo(Integer compId, String compUccn) {
        Company comp = super.getById(compId);
        comp.setCompUccn(compUccn);
        super.updateById(comp);
        return compUccn;
    }

    @Override
    public String saveOrUpdateQYDZInfo(Integer compId, String compUrl) {
        Company comp = super.getById(compId);
        comp.setCompUccn(compUrl);
        super.updateById(comp);
        return compUrl;
    }

    @Override
    public Company saveOrUpdateGSXXInfo(Company company) {
        Integer id = company.getId();
        Company companyDB = super.getById(id);
        if(company.getBusinessLicenseFile() != null){
            //处理营业执照文件名称businessLicenseName
            MultipartFile businessLicenseFile = company.getBusinessLicenseFile();
            StringBuffer businessLicenseFilePath = new StringBuffer();
            String businessLicenseName = fileUtils.uploadFile(businessLicenseFile, businessLicenseFilePath);
            companyDB.setBusinessLicenseName(businessLicenseName);
            companyDB.setBusinessLicenseUrl(businessLicenseFilePath.toString());
        }
        companyDB.setCompFullName(company.getCompFullName());
        companyDB.setCompLegalPerson(company.getCompLegalPerson());
        companyDB.setCompCapital(company.getCompCapital());
        companyDB.setCompType(company.getCompType());
        companyDB.setCompUnicode(company.getCompUnicode());
        super.updateById(companyDB);
        return companyDB;
    }
}
