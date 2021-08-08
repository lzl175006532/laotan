package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.mapper.EducationHistoryMapper;
import com.laotan.net.entity.EducationHistory;
import com.laotan.net.service.EducationHistoryService;
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
public class EducationHistoryServiceImpl extends ServiceImpl<EducationHistoryMapper, EducationHistory> implements EducationHistoryService {

    Logger logger = LoggerFactory.getLogger(EducationHistoryServiceImpl.class);
    @Autowired
    private EducationHistoryMapper educationHistoryMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        LambdaQueryWrapper<EducationHistory> wrapper = new LambdaQueryWrapper();
        wrapper.eq(EducationHistory :: getUserId,userId);
        int delete = educationHistoryMapper.delete(wrapper);
        return delete;
    }

    @Override
    public List<EducationHistory> selectByUserId(Integer userId) {
        LambdaQueryWrapper<EducationHistory> wrapper = new LambdaQueryWrapper();
        wrapper.eq(EducationHistory :: getUserId,userId);
        wrapper.orderByDesc(EducationHistory :: getInSchoolTime);
        List<EducationHistory> educationHistories = educationHistoryMapper.selectList(wrapper);
        return educationHistories;
    }

    @Override
    public Integer deleteById(Integer educationHistoryId) {
        return educationHistoryMapper.deleteById(educationHistoryId);
    }
}
