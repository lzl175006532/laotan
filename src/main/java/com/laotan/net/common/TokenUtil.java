package com.laotan.net.common;


import com.laotan.net.entity.Account;
import org.springframework.util.StringUtils;


/**
 * @Author: lzl
 * @Date: 2019/11/5 11:39
 * @Description：
 */
public class TokenUtil {

    static final String salt = "AHJKFDSAIPQWF435DSA1F321D564T.10.FDSA12F3DSA45F3DSA21TRY";

    public static Account createTokenByAccout(Account account){
        String encrypt = "";
        try {
            encrypt = DESUtil.encrypt(account.getId() + "", salt);
            account.setToken(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public static Integer getAccoutIdByToken(String token){
        String decryptToken = "";//token解密结果
        try {
            decryptToken = DESUtil.decrypt(token, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isEmpty(decryptToken)){
            return 0;
        }else{
            return Integer.valueOf(decryptToken);
        }
    }

}
