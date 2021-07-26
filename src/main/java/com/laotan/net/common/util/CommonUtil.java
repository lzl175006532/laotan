package com.laotan.net.common.util;

import java.lang.reflect.Field;

/**
 * @Copyright 通泰信诚
 * @Author lizilong
 * @Description
 * @Date 18:18$ 2021/7/26$
 **/
public class CommonUtil {
    /**
     * 解决更新时，源对象被覆盖
     * @param src 数据库查出的源对象数据
     * @param desc 前端传的需要更新的数据
     * @return 更新后的对象
     * @throws Exception 源对象与目标对象类型不一致
     */
    public static Object updateBean(Object src,Object desc) throws Exception {
        Class<?> srcClass = src.getClass();
        Class<?> descClass = desc.getClass();
        if(!descClass.equals(srcClass)){
            throw new Exception("源对象与目标对象类型不一致！");
        }

        Field[] srcClassFields = srcClass.getDeclaredFields();
        Field[] descClassFields = descClass.getDeclaredFields();

        for (int i = 0; i < descClassFields.length; i++) {
            descClassFields[i].setAccessible(true);
            Object o = descClassFields[i].get(desc);
            if(o != null && !"".equals(o.toString().trim())){
                srcClassFields[i].setAccessible(true);
                srcClassFields[i].set(src, o);
            }
        }

        return src;
    }
}
