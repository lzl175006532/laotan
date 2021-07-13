package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.ProjectHistory;
import com.laotan.net.entity.WorkHistory;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface ProjectHistoryService extends IService<ProjectHistory> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:10
     * @Params: [userId]
     * @Return: java.lang.Integer
     * @Description: 根据用户id删除工作经历
     */
    Integer deleteByUserId(Integer userId);
}
