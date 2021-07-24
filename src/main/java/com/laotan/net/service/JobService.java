package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.Job;

import java.util.List;

public interface JobService extends IService<Job> {

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/22 17:44
     * @Params: [code]
     * @Return: com.laotan.net.entity.Job
     * @Description: 根据code查询
     */
   Job selectByCode(String code);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/22 17:44
     * @Params: [code]
     * @Return: com.laotan.net.entity.Job
     * @Description: 根据父级节点查询列表
     */
    List<Job> selectByParentId(String parentId);
}
