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
     * @Description: 保存或更新用户基本信息
     */
    User saveOrUpdateJBXXInfo(User user);

    /*
    根据token获取用户信息
     */
    User selectUserInfoByToken(String token);

    /*
    保存个人优势
     */
    User saveOrUpdateGRYSInfo(User user);
}
