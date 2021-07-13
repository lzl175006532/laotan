package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.JobIntentionMapper;
import com.laotan.net.controller.mapper.UserMapper;
import com.laotan.net.entity.JobIntention;
import com.laotan.net.entity.User;
import com.laotan.net.service.JobIntentionService;
import com.laotan.net.service.UserService;
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
public class JobIntentionServiceImpl extends ServiceImpl<JobIntentionMapper, JobIntention> implements JobIntentionService {

    Logger logger = LoggerFactory.getLogger(JobIntentionServiceImpl.class);
    @Autowired
    private JobIntentionMapper jobIntentionMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        LambdaQueryWrapper<JobIntention> wrapper = new LambdaQueryWrapper();
        wrapper.eq(JobIntention :: getUserId,userId);
        int delete = jobIntentionMapper.delete(wrapper);
        return delete;
    }
}
