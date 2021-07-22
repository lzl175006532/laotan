package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.mapper.ProjectHistoryMapper;
import com.laotan.net.entity.ProjectHistory;
import com.laotan.net.service.ProjectHistoryService;
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
public class ProjectHistoryServiceImpl extends ServiceImpl<ProjectHistoryMapper, ProjectHistory> implements ProjectHistoryService {

    Logger logger = LoggerFactory.getLogger(ProjectHistoryServiceImpl.class);
    @Autowired
    private ProjectHistoryMapper projectHistoryMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        LambdaQueryWrapper<ProjectHistory> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ProjectHistory :: getUserId,userId);
        int delete = projectHistoryMapper.delete(wrapper);
        return delete;
    }
}
