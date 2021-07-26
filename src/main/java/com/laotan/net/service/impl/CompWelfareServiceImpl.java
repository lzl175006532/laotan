package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.mapper.CompWelfareMapper;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.service.CompWelfareService;
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
public class CompWelfareServiceImpl extends ServiceImpl<CompWelfareMapper, CompWelfare> implements CompWelfareService {

    Logger logger = LoggerFactory.getLogger(CompWelfareServiceImpl.class);

    @Autowired
    private CompWelfareMapper compWelfareMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        return compWelfareMapper.deleteById(userId);
    }

    @Override
    public CompWelfare addForCompany(String welfareName, Integer compId, String type) {
        CompWelfare compWelfare = new CompWelfare();
        compWelfare.setName(welfareName);
        compWelfare.setCompId(compId);
        compWelfare.setType(type);
        super.save(compWelfare);
        return compWelfare;
    }

    @Override
    public Integer delForCompany(String welfareName, Integer compId, String type) {
        LambdaQueryWrapper<CompWelfare> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(CompWelfare :: getName,welfareName);
        queryWrapper.eq(CompWelfare :: getType,type);
        queryWrapper.eq(CompWelfare :: getCompId,compId);
        return compWelfareMapper.delete(queryWrapper);
    }

    @Override
    public List<CompWelfare> selectByCompId(Integer compId) {
        LambdaQueryWrapper<CompWelfare> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(CompWelfare :: getCompId,compId);
        return compWelfareMapper.selectList(queryWrapper);
    }
}
