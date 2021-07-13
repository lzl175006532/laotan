package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.WorkHistoryMapper;
import com.laotan.net.entity.ProjectHistory;
import com.laotan.net.entity.WorkHistory;
import com.laotan.net.service.WorkHistoryService;
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
public class WorkHistoryServiceImpl extends ServiceImpl<WorkHistoryMapper, WorkHistory> implements WorkHistoryService {

    Logger logger = LoggerFactory.getLogger(WorkHistoryServiceImpl.class);
    @Autowired
    private WorkHistoryMapper workHistoryMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        LambdaQueryWrapper<WorkHistory> wrapper = new LambdaQueryWrapper();
        wrapper.eq(WorkHistory :: getUserId,userId);
        int delete = workHistoryMapper.delete(wrapper);
        return delete;
    }
}
