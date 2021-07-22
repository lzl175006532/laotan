package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.Job;
import com.laotan.net.entity.User;
import com.laotan.net.mapper.AccountMapper;
import com.laotan.net.mapper.JobMapper;
import com.laotan.net.service.AccountService;
import com.laotan.net.service.JobService;
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
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Autowired
    private JobMapper jobMapper;


    @Override
    public Job selectByCode(String code) {
        return jobMapper.selectByCode(code);
    }
}
