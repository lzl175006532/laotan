package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.util.FileUtils;
import com.laotan.net.mapper.BossMapper;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.CompImg;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.entity.Company;
import com.laotan.net.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private SystemParamService systemParamService;

    @Override
    public Boss saveBossInfo(Boss boss) {
        super.save(boss);
        return boss;
    }

    @Override
    public Boss saveOrUpdateBossInfo(Boss boss) {
        Integer id = boss.getId();
        //处理附件:认证视频信息
        if(boss != null && boss.getAuthFile() != null){
            StringBuffer filePath = new StringBuffer();
            String fileName = fileUtils.uploadFile(boss.getAuthFile(), filePath);
            boss.setAuthFileUrl(filePath.toString());
            boss.setAuthFileName(fileName);
            //根据身份证号码判断男女,倒数第二位是奇数则为男性
            String idNumber = boss.getIdNumber();
            if(!StringUtils.isEmpty(idNumber) && (Integer.valueOf(idNumber.substring(idNumber.length() - 2,idNumber.length() - 1))&1) == 1 ){
                boss.setSex("男");
            }
        }
        //处理附件:头像信息
        if(boss != null && boss.getHeadImgFile() != null){
            StringBuffer filePath = new StringBuffer();
            String fileName = fileUtils.uploadFile(boss.getHeadImgFile(), filePath);
            boss.setHeadImgUrl(filePath.toString());
            boss.setHeadImgName(fileName);
        }
        if(id == null || id == 0){
            //新增
            boss.setCreateTime(LocalDateTime.now());
            boss.setUpdateTime(LocalDateTime.now());
            return this.saveBossInfo(boss);
        }else{
            //更新boss对象
            boss.setUpdateTime(LocalDateTime.now());
            super.updateById(boss);
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
