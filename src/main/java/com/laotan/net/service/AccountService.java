package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.common.JsonResult;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.User;

public interface AccountService extends IService<Account> {

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 17:37
     * @Params: [cellPhone]
     * @Return: com.laotan.net.entity.Account
     * @Description: 通过手机号查找账号
     */
    Account findByCellPhone(String cellPhone);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 17:38
     * @Params: [accountDB]
     * @Return: com.laotan.net.entity.User
     * @Description: 手机号登录
     */
    JsonResult login(String cellPhone , String verifyCode, String password, String type);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/23 15:49
     * @Params: [cellPhone, password]
     * @Return: com.laotan.net.entity.Account
     * @Description: 设置密码
     */
    Account setPassword(String cellPhone, String password);

    /*
    根据手机号查询账号信息
     */
    Account selectByCellPhone(String cellPhone);

    /*
    根据手机号查询账号信息
     */
    Account selectByToken(String token);
}
