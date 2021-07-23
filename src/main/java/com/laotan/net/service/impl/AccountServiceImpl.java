package com.laotan.net.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laotan.net.common.MD5Util;
import com.laotan.net.mapper.AccountMapper;
import com.laotan.net.entity.*;
import com.laotan.net.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Account findByCellPhone(String cellPhone) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Account :: getCellPhone,cellPhone);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public Boolean login(String cellPhone , String verifyCode,String password,String type) {
        Account accountDB = this.findByCellPhone(cellPhone);
        if(accountDB == null){
            //新用户，需要注册
            accountDB = new Account();
            accountDB.setCellPhone(cellPhone);
            accountDB.setCreateTime(LocalDateTime.now());
            accountDB = MD5Util.getMD5Str2Account(accountDB);
            super.save(accountDB);
            return true;
        }

        if("PASSWORD".equals(type)){
            //密码方式登录,校验密码
            String salt = accountDB.getSalt();
            String md5Str = MD5Util.getMD5Str(password+salt);
            if(accountDB != null && accountDB.getPassword().equals(md5Str)){
                return true;
            }else{
                return false;
            }
        }else{
            //短信验证码方式
            // TODO: 2021/7/23
        }
        return null;
    }

    @Override
    public Account setPassword(String cellPhone, String password) {
        Account accountDB = this.findByCellPhone(cellPhone);
        if(accountDB == null){
            //新注册用户
            accountDB = new Account();
            accountDB.setCellPhone(cellPhone);
            accountDB.setCreateTime(LocalDateTime.now());
        }else{
            //数据库已存在用户
            accountDB.setUpdateTime(LocalDateTime.now());
        }
        //设置密码
        accountDB = MD5Util.getMD5Str2Account(accountDB);
        super.saveOrUpdate(accountDB);
        return accountDB;
    }
}
