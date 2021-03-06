package com.laotan.net.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.PublishJob;
import com.laotan.net.vo.SearchJobVO;

import java.util.List;

public interface PublishJobService extends IService<PublishJob> {

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/19 17:10
     * @Params:
     * @Return:
     * @Description: 保存发布职位
     */
    Boolean saveInfo(PublishJob publishJob);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/19 17:10
     * @Params: [cellPhone, identity]
     * @Return: java.util.List<com.laotan.net.entity.PublishJob>
     * @Description: 根据用户手机号和用户身份查询职位列表：应聘者查询与自己求职意向匹配的职位列表，招聘者查询自己发布的职位列表
     */
    IPage<PublishJob> selectJobByCellPhone(SearchJobVO searchJobVO);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/23 16:50
     * @Params: [searchJobVO]
     * @Return: com.baomidou.mybatisplus.core.metadata.IPage<com.laotan.net.entity.PublishJob>
     * @Description: 根据参数searchJobVO查询发布职位信息
     */
    IPage<PublishJob> selectJobList(SearchJobVO searchJobVO);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 20:06
     * @Params: [jobId]
     * @Return: com.laotan.net.entity.PublishJob
     * @Description: 根据职位id查询详情
     */
    PublishJob selectById(Integer jobId);
}
