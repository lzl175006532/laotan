package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.CompImg;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface CompImgService extends IService<CompImg> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:05
     * @Params: [id]
     * @Return: java.lang.Integer
     * @Description: 根据id删除
     */
    Integer deleteById(Integer id);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 20:01
     * @Params: [compId]
     * @Return: java.util.List<com.laotan.net.entity.CompImg>
     * @Description: 根据公司id查询
     */
    List<CompImg> selectByCompId(Integer compId);

}
