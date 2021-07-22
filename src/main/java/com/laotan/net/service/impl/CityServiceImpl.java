package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.mapper.CityMapper;
import com.laotan.net.entity.City;
import com.laotan.net.service.CityService;
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
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> selectByType(Integer type) {
        LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(City::getType,type);
        return cityMapper.selectList(queryWrapper);
    }

    @Override
    public List<City> selectNextListById(Integer parentId) {
        LambdaQueryWrapper<City> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(City::getPid,parentId);
        return cityMapper.selectList(queryWrapper);
    }
}
