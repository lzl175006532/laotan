package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.mapper.CompImgMapper;
import com.laotan.net.entity.CompImg;
import com.laotan.net.service.CompImgService;
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
public class CompImgServiceImpl extends ServiceImpl<CompImgMapper, CompImg> implements CompImgService {

    Logger logger = LoggerFactory.getLogger(CompImgServiceImpl.class);

    @Autowired
    private CompImgMapper compImgMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        return compImgMapper.deleteById(userId);
    }

    @Override
    public List<CompImg> selectByCompId(Integer compId) {
        LambdaQueryWrapper<CompImg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CompImg ::getCompId,compId);
        return compImgMapper.selectList(queryWrapper);
    }
}
