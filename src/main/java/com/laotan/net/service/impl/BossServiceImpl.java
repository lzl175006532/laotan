package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.BossMapper;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.entity.Company;
import com.laotan.net.service.BossService;
import com.laotan.net.service.CompImgService;
import com.laotan.net.service.CompWelfareService;
import com.laotan.net.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BossServiceImpl extends ServiceImpl<BossMapper, Boss> implements BossService {

    Logger logger = LoggerFactory.getLogger(BossServiceImpl.class);
    @Autowired
    private BossMapper bossMapper;
    @Autowired
    private CompImgService compImgService;
    @Autowired
    private CompWelfareService compWelfareService;
    @Autowired
    private CompanyService companyService;

    @Override
    public Boss saveBossInfo(Boss boss) {
        Company company = boss.getCompany();
        boolean save = super.save(boss);
        company.setBossId(boss.getId());
        companyService.save(company);
        if(save){
            //保存企业相册
            List<CompImg> imgList = company.getImgList();
            if(imgList != null && imgList.size() > 0){
                for (CompImg compImg:imgList) {
                    compImg.setCompId(company.getId());
                }
                compImgService.saveBatch(imgList);
            }
            //保存企业福利
            List<CompWelfare> comWelfareList = company.getComWelfareList();
            if(comWelfareList != null && comWelfareList.size() > 0){
                for (CompWelfare compWelfare:comWelfareList) {
                    compWelfare.setCompId(company.getId());
                }
                compWelfareService.saveBatch(comWelfareList);
            }
        }

        return boss;
    }

    @Override
    public Boss saveOrUpdateBossInfo(Boss boss) {
        Integer id = boss.getId();
        Company company = boss.getCompany();
        if(id == null || id == 0){
            //新增
            return this.saveBossInfo(boss);
        }else{
            //更新boss对象
            super.updateById(boss);
            //更新公司信息
            companyService.updateCompanyInfo(company);
        }
        return boss;
    }


    @Override
    public Boss selectUserInfoByCellPhone(String cellPhone) {
        LambdaQueryWrapper<Boss> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Boss :: getCellPhone,cellPhone);
        return bossMapper.selectOne(wrapper);
    }

}
