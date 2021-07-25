package com.laotan.net.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laotan.net.common.JsonResult;
import com.laotan.net.entity.Account;
import com.laotan.net.entity.UserToken;

public interface UserTokenService extends IService<UserToken> {


    /**
    * @Description：根据账号account查询userToken对象
    * @Author  lzl
    * @Date   2020/6/29 21:37
    * @Param
    * @Return
    */
    UserToken getUserTokenByAccount(String accout);
    /**
    * @Description：根据账号account生成token
    * @Author  lzl
    * @Date   2020/6/29 21:37
    * @Param
    * @Return
    */
    String createUserToken(Account account);

    /**
    * @Description：通过token查询UserToken对象
    * @Author  lzl
    * @Date   2020/6/29 21:37
    * @Param
    * @Return
    */
    UserToken getByToken(String clientToken);


    /**
    * @Description：根据refreshToken重新生成token
    * @Author  lzl
    * @Date   2020/6/29 22:30
    * @Param
    * @Return
    */
    UserToken refreshToken(String refreshToken);


    /**
     *  @author: lzl
     *  @Date: 2020/10/29 11:26
     *  @Description:根据用户token验证有效性并返回user对象
     */
    JsonResult authToken(String clientToken);
}
