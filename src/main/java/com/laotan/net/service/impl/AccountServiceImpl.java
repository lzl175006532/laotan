package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.controller.mapper.AccountMapper;
import com.laotan.net.controller.mapper.UserMapper;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzl
 * @since 2019-10-23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;


    @Override
    public Account findByCellPhone(String cellPhone,String loginType) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Account :: getCellPhone,cellPhone);
        wrapper.eq(Account :: getLoginType,loginType);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public User login(Account accountDB) {
        return null;
    }
}
