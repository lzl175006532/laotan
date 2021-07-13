package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.Boss;
import com.laotan.net.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface BossService extends IService<Boss> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:03
     * @Params: [user]
     * @Return: com.laotan.net.entity.User
     * @Description: 新增用户信息
     */
    Boss saveOrUpdateBossInfo(Boss boss);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 21:12
     * @Params: [cellPhone, loginType]
     * @Return: com.laotan.net.entity.User
     * @Description: 根据手机号获取用户信息
     */
    Boss selectUserInfoByCellPhone(String cellPhone);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 22:27
     * @Params: [boss]
     * @Return: com.laotan.net.entity.Boss
     * @Description: 保存或更新招聘者用户注册信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可
     */
    Boss saveBossInfo(Boss boss);
}
