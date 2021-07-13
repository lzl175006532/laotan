package com.laotan.net.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:json格式返回结果
 * @version 1.0
 * @author: 李子龙
 * @date: 2019/4/26 15:20
 */
@Data
public class JsonResult<T> {
    protected Integer code;
    protected String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    protected Date date = new Date();

    public JsonResult() {
    }

    public JsonResult(Integer code) {
        this.setCode(code);
    }

    public JsonResult(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public JsonResult(Integer code, String message, T content) {
        this.setCode(code);
        this.setMessage(message);
        this.setContent(content);
    }

    public JsonResult(ResultStatusCode dbResourceNull) {
        this.setCode(dbResourceNull.getCode());
        this.setMessage(dbResourceNull.getMessage());
    }

    public JsonResult(ResultStatusCode dbResourceNull, T content) {
        this.setCode(dbResourceNull.getCode());
        this.setMessage(dbResourceNull.getMessage());
        this.setContent(content);
    }
    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
