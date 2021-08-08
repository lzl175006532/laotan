package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.CompWelfare;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface CompWelfareService extends IService<CompWelfare> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:05
     * @Params: [id]
     * @Return: java.lang.Integer
     * @Description: 根据公司id删除所有公司福利
     */
    Integer deleteByCompId(Integer compId);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 18:53
     * @Params: [welfareName, compId, type]
     * @Return: com.laotan.net.entity.CompWelfare
     * @Description: 为公司添加福利
     */
    CompWelfare addForCompany(String welfareName, Integer compId, String type);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 18:53
     * @Params: [welfareName, compId, type]
     * @Return: com.laotan.net.entity.CompWelfare
     * @Description: 为公司删除福利
     */
    Integer delForCompany(String welfareName, Integer compId, String type);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/26 20:03
     * @Params: [compId]
     * @Return: java.util.List<com.laotan.net.entity.CompWelfare>
     * @Description: 根据公司id查询
     */
    List<CompWelfare> selectByCompId(Integer compId);
}
