package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
public interface UserService extends IService<User> {


    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 18:03
     * @Params: [user]
     * @Return: com.laotan.net.entity.User
     * @Description: 新增用户信息
     */
    User saveUserInfo(User user);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 21:12
     * @Params: [cellPhone, loginType]
     * @Return: com.laotan.net.entity.User
     * @Description: 根据手机号获取用户信息
     */
    User selectUserInfoByCellPhone(String cellPhone);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 22:21
     * @Params:
     * @Return:
     * @Description: 保存或更新用户注册简历信息,如果是修改：修改哪个哪个不为空，并且id不为空，其他为空即可
     */
    User saveOrUpdateInfo(User user);
}
