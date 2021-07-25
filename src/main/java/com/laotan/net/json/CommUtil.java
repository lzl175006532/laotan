package com.laotan.net.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: ttxc
 * @Date: 2019/10/30 16:41
 * @Description:
 */
public class CommUtil {
    /**
    * 功能描述: 前端vue传的字符串带[]需要转一下
    * @Author: lzl
    * @Date: 2019/10/30
    **/
    public static List stringToList(String source){
        if(StringUtils.isEmpty(source)){
            return null;
        }
        JSONArray arr = JSON.parseArray(source);
        return Arrays.asList(arr).get(0);
    }

    /** 
    * 功能描述: json转对象list
    * @Author: liubo
    * @Date: 2019/11/4
    **/
    public static<T> List<T> stringToObjectList(String source,Class<T> model){
        if(StringUtils.isEmpty(source)){
            return null;
        }
        List<T> object = (List<T>) JSONArray.parseArray(source, model);
        return object;
    }
}
