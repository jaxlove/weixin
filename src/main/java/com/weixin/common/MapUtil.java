package com.weixin.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdejun
 * @description: Map工具类
 * @date 2019/11/18 18:36
 */
public class MapUtil {

    /**
     * 转换bean为map
     *
     * @param source 要转换的bean
     * @param <T>    bean类型
     * @return 转换结果
     */
    public static <T> Map<String, Object> bean2Map(T source) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();

        Class<?> sourceClass = source.getClass();
        //拿到所有的字段,不包括继承的字段
        Field[] sourceFiled = sourceClass.getDeclaredFields();
        for (Field field : sourceFiled) {
            field.setAccessible(true);//设置可访问,不然拿不到private
            //配置了注解的话则使用注解名称,作为header字段
            result.put(field.getName(), field.get(source));
        }
        return result;
    }

    /**
     * map转bean
     *
     * @param source   map属性
     * @param instance 要转换成的备案
     * @return 该bean
     */
    public static <T> T map2Bean(Map source, Class<T> instance) {
        if(source == null){
            return null;
        }
        String s = GsonTool.gsonString(source);
        return GsonTool.gsonToBean(s,instance);
    }
}
