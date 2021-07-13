package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
    Account findByCellPhone(String cellPhone,String loginType);

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/13 17:38
     * @Params: [accountDB]
     * @Return: com.laotan.net.entity.User
     * @Description: 手机号登录
     */
    User login(Account accountDB);
}
