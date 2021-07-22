package com.laotan.net.handleException;

import com.laotan.net.common.ResultStatusCode;
import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private Integer code;

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:50
     * @Params: [type]
     * @Return:
     * @Description: 使用已有的错误类型
     */
    public CustomException(ResultStatusCode type){
        super(type.getMessage());
        this.code = type.getCode();
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:50
     * @Params: [code, msg]
     * @Return:
     * @Description: 自定义错误类型
     */
    public CustomException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
