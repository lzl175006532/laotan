package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.City;

import java.util.List;

public interface CityService extends IService<City> {

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/22 16:51
     * @Params: [type]
     * @Return: java.util.List<com.laotan.net.entity.City>
     * @Description: 根据指定层级类型查询type（0-中国，1-省级，2-市级，3-县级）
     */
    List<City> selectByType(Integer type);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/22 16:54
     * @Params: [parentId]
     * @Return: java.util.List<com.laotan.net.entity.City>
     * @Description: 指定城市id查询下一级城市集合
     */
    List<City> selectNextListById(Integer parentId);
}
