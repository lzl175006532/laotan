package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.CommenEnum;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.mapper.PublishJobMapper;
import com.laotan.net.entity.*;
import com.laotan.net.handleException.CustomException;
import com.laotan.net.service.*;
import com.laotan.net.vo.SearchJobVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class PublishJobServiceImpl extends ServiceImpl<PublishJobMapper, PublishJob> implements PublishJobService {

    Logger logger = LoggerFactory.getLogger(PublishJobServiceImpl.class);
    @Autowired
    private PublishJobMapper publishJobMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JobIntentionService jobIntentionService;
    @Autowired
    private BossService bossService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CompanyService companyService;

    @Override
    public Boolean saveInfo(PublishJob publishJob) {
        StringBuffer addressPre = new StringBuffer();
        Integer addressProvinceId = publishJob.getAddressProvinceId();
        if(addressProvinceId != null && addressProvinceId != 0){
            City province = cityService.getById(addressProvinceId);
            if(province != null){
                addressPre.append(province.getCityname());
            }
        }
        Integer addressCityId = publishJob.getAddressCityId();
        if(addressCityId != null && addressCityId != 0){
            City city = cityService.getById(addressCityId);
            if(city != null){
                addressPre.append(city.getCityname());
            }
        }
        Integer addressCountyId = publishJob.getAddressCountyId();
        if(addressCountyId != null && addressCountyId != 0){
            City county = cityService.getById(addressCountyId);
            if(county != null){
                addressPre.append(county.getCityname());
            }
        }
        addressPre.append(publishJob.getAddressDetail());
        publishJob.setAddressDetail(addressPre.toString());
        publishJob.setCreateTime(LocalDateTime.now());
        publishJob.setUpdateTime(LocalDateTime.now());
        return super.save(publishJob);
    }

    @Override
    public IPage<PublishJob> selectJobByCellPhone(SearchJobVO searchJobVO) {
        //关键字模糊查询
        String keyword = searchJobVO.getKeyword();
        String identity = searchJobVO.getIdentity();
        String cellPhone = searchJobVO.getCellPhone();
        String jobName = searchJobVO.getJobName();
        //省
        Integer provinceId = searchJobVO.getProvinceId();
        //市
        Integer cityId = searchJobVO.getCityId();
        //区、县
        Integer countyId = searchJobVO.getCountyId();

        Page page = searchJobVO.getPage();
        IPage<PublishJob> publishJobIPage = null;

        //应聘者查询职位列表
        if(CommenEnum.USER.equals(identity)){
            //根据应聘者求职意向查询职位信息集合
            User user = userService.selectUserInfoByCellPhone(cellPhone);
            if(user == null){
                throw new CustomException(ResultStatusCode.ACCOUNT_USER_NOT_EXIST);
            }
            //如果查询条件compName和jobName都为空则根据用户简历中期望职位来推荐职位信息
            if(StringUtils.isEmpty(keyword) && StringUtils.isEmpty(jobName)){
                List<JobIntention> jobIntentionList = jobIntentionService.selectByUserId(user.getId());
                List<String> jobIntentionNameList = new ArrayList<>();
                if(jobIntentionList != null && jobIntentionList.size() > 0){
                    for (JobIntention jobIntention:jobIntentionList) {
                        jobIntentionNameList.add(jobIntention.getExpectJobName());
                    }
                }
                publishJobIPage = publishJobMapper.selectJobByJobIntentionNameList(page,jobIntentionNameList);
            }else{
                //根据用户筛选条件筛选:城市、职位
                LambdaQueryWrapper<PublishJob> queryWrapper = new LambdaQueryWrapper();
                if(!StringUtils.isEmpty(keyword)){
                    queryWrapper.like(PublishJob::getAddressDetail,keyword);
                    queryWrapper.or();
                    queryWrapper.like(PublishJob::getJobName,keyword);
                    queryWrapper.or();
                    queryWrapper.like(PublishJob::getCompName,keyword);
                }
                if(!StringUtils.isEmpty(jobName)){
                    queryWrapper.like(PublishJob::getJobName,jobName);
                }
                if(provinceId != null){
                    queryWrapper.eq(PublishJob::getAddressProvinceId,provinceId);
                }
                if(provinceId != null){
                    queryWrapper.eq(PublishJob::getAddressCityId,cityId);
                }
                if(provinceId != null){
                    queryWrapper.eq(PublishJob::getAddressCountyId,countyId);
                }
                publishJobIPage = publishJobMapper.selectPage(page, queryWrapper);
            }

        }
        //招聘者查询发布职位列表
        if(CommenEnum.BOSS.equals(identity)){
            //根据HR手机号查询已发布的职位信息
            Boss boss = bossService.selectUserInfoByCellPhone(cellPhone);
            publishJobIPage = publishJobMapper.selectJobByBossId(page,boss.getId());
        }
        return publishJobIPage;
    }

    @Override
    public IPage<PublishJob> selectJobList(SearchJobVO searchJobVO) {
        return null;
    }

    @Override
    public PublishJob selectById(Integer jobId) {
        return publishJobMapper.selectById(jobId);
    }
}
