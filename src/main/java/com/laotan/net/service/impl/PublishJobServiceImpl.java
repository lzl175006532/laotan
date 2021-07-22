package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.CommenEnum;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.mapper.PublishJobMapper;
import com.laotan.net.entity.*;
import com.laotan.net.handleException.CustomException;
import com.laotan.net.service.*;
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

    @Override
    public Boolean saveInfo(PublishJob publishJob) {
        publishJob.setCreateTime(LocalDateTime.now());
        publishJob.setUpdateTime(LocalDateTime.now());
        return super.save(publishJob);
    }

    @Override
    public List<PublishJob> selectJobByCellPhone(String cellPhone, String identity) {
        List<PublishJob> publishJobList = new ArrayList<>();
        //应聘者查询职位列表
        if(CommenEnum.USER.equals(identity)){
            //根据应聘者求职意向查询职位信息集合
            User user = userService.selectUserInfoByCellPhone(cellPhone);
            if(user == null){
                throw new CustomException(ResultStatusCode.ACCOUNT_USER_NOT_EXIST);
            }
            List<JobIntention> jobIntentionList = jobIntentionService.selectByUserId(user.getId());
            List<String> jobIntentionNameList = new ArrayList<>();
            if(jobIntentionList != null && jobIntentionList.size() > 0){
                for (JobIntention jobIntention:jobIntentionList) {
                    jobIntentionNameList.add(jobIntention.getExpectJobName());
                }
            }
            publishJobList = publishJobMapper.selectJobByJobIntentionNameList(jobIntentionNameList);
        }
        //招聘者查询发布职位列表
        if(CommenEnum.BOSS.equals(identity)){
            //根据HR手机号查询已发布的职位信息
            Boss boss = bossService.selectUserInfoByCellPhone(cellPhone);
            publishJobList = publishJobMapper.selectJobByBossId(boss.getId());
        }
        return publishJobList;
    }
}
