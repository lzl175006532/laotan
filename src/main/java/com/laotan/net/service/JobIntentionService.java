package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.JobIntention;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface JobIntentionService extends IService<JobIntention> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:05
     * @Params: [id]
     * @Return: java.lang.Integer
     * @Description: 根据用户id删除求职意向
     */
    Integer deleteByUserId(Integer userId);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/19 17:16
     * @Params: [id]
     * @Return: java.util.List<com.laotan.net.entity.JobIntention>
     * @Description: 根据用户id查询用户求职意向集合
     */
    List<JobIntention> selectByUserId(Integer id);
}
