package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.WorkHistory;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface WorkHistoryService extends IService<WorkHistory> {


    Integer deleteByUserId(Integer userId);
}
