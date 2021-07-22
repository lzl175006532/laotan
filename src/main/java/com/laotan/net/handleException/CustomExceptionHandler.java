package com.laotan.net.handleException;

import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:50
     * @Params: [ce]
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 相当于controller的@RequestMapping,处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public JsonResult customExceptionHandle(CustomException ce){
        logger.error(ce.getMessage());
        return new JsonResult(ce.getCode(),ce.getMessage());
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:51
     * @Params: []
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public JsonResult nullPointerExceptionHandle(Exception e){
        logger.error(e.getMessage());
        JsonResult jsonResult = new JsonResult(ResultStatusCode.NOT_NULL);
        return jsonResult;
    }

    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/5/21 11:51
     * @Params: []
     * @Return: com.ttxc.newenergy.common.JsonResult
     * @Description: 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exceptionHandle(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage());
        return new JsonResult(ResultStatusCode.SYSTEM_ERR);
    }
}
