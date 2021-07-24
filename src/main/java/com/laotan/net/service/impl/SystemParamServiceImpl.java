package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.entity.Job;
import com.laotan.net.mapper.SystemParamMapper;
import com.laotan.net.entity.SystemParam;
import com.laotan.net.service.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzl
 * @since 2019-10-29
 */
@Service
public class SystemParamServiceImpl extends ServiceImpl<SystemParamMapper, SystemParam> implements SystemParamService {
    
    @Autowired
    SystemParamMapper systemParamMapper;
    @Override
    public IPage<SystemParam> pageSelectList(Page<SystemParam> page) {
        return systemParamMapper.pageSelectList(page);
    }

    @Override
    public List selectList() {
        QueryWrapper wrapper = new QueryWrapper<>();
        List list = systemParamMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Integer insert(SystemParam systemParam) {
        systemParam.setCreateTime(LocalDateTime.now());
        systemParam.setUpdateTime(LocalDateTime.now());
        return systemParamMapper.insert(systemParam);
    }

    @Override
    public Integer delete(List<String> ids) {
        int i = systemParamMapper.deleteBatchIds(ids);
        return i;
    }

    @Override
    public Integer deleteById(Integer id) {
        return systemParamMapper.deleteById(id);
    }

    @Override
    public Integer update(SystemParam systemParam) {
        systemParam.setUpdateTime(LocalDateTime.now());
        return systemParamMapper.updateById(systemParam);
    }

    @Override
    public SystemParam selectOne(Integer id) {
        return systemParamMapper.selectById(id);
    }

    @Override
    public String selectByParamKey(String paramName,String type) {
        return systemParamMapper.selectByParamKey(paramName,type);
    }

    @Override
    public List<SystemParam> selectByType(String type) {
        LambdaQueryWrapper<SystemParam> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SystemParam::getType,type);
        return systemParamMapper.selectList(queryWrapper);
    }
}
