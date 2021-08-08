package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.WorkHistory;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface WorkHistoryService extends IService<WorkHistory> {


    /*
    根据用户id删除
     */
    Integer deleteByUserId(Integer userId);

    /*
    根据用户id查询
     */
    List<WorkHistory> selectByUserId(Integer userId);

    /*
    根据id删除
     */
    Integer deleteById(Integer workHistoryId);
}
