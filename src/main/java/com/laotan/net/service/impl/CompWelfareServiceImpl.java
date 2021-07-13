package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.CompWelfareMapper;
import com.laotan.net.entity.CompWelfare;
import com.laotan.net.service.CompWelfareService;
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
public class CompWelfareServiceImpl extends ServiceImpl<CompWelfareMapper, CompWelfare> implements CompWelfareService {

    Logger logger = LoggerFactory.getLogger(CompWelfareServiceImpl.class);

    @Autowired
    private CompWelfareMapper compWelfareMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        return compWelfareMapper.deleteById(userId);
    }
}
