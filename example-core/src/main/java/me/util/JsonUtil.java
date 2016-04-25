package me.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

/**
 * Created by chn on 16/4/22.
 */
public class JsonUtil {

    /**
     * 将一个对象转换成一个Json.
     *
     * @param o
     * @return
     */

    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }

    /**
     * 将一个json转换成clazz类型的对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>Object toJavaObject(String json, Class<T> clazz ) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 判断一个一般性的json是否可以转换成clazz的对象.
     *
     * 将json(字符串头部不带类名的json)转换成一个JSONObject,然后逐项检查JSONObject的key是否存在于clazz类的可访问的元素中.
     * @param json
     * @param clazz
     * @return
     */
    //@Deprecated
    public static boolean canParse(String json, Class clazz) {
        JSONObject o;
        try {
            o = JSON.parseObject(json);
        } catch(JSONException e) {
            return false;
        }
        Set<String> accessableField = Sets.newHashSet();

        for(Class superClazz = clazz; superClazz!=Object.class; superClazz = superClazz.getSuperclass()) {
            try {
                Field[] fields = superClazz.getDeclaredFields();
                if(fields != null) {
                    for (Field field : fields) {
                        // 如果field是public,加入.
                        if (Modifier.isPublic(field.getModifiers())) {
                            accessableField.add(field.getName());
                        }
                    }
                }
                Method[] methods = superClazz.getDeclaredMethods();
                if(methods!=null) {
                    for(Method method: methods) {
                        // 如果存在public getAbc或isAbc, 把abc加入,
                        String mn = method.getName();
                        if(Modifier.isPublic(method.getModifiers())) {
                            StringBuilder fieldName = null;
                            // Abc
                            if(mn.startsWith("get")) {
                                fieldName = new StringBuilder(mn.substring(3));
                            } else if(mn.startsWith("is")) {
                                fieldName = new StringBuilder(mn.substring(2));
                            }
                            // abc
                            if(!Strings.isEmpty(fieldName)) {
                                fieldName.setCharAt(0, Character.toLowerCase(fieldName.charAt(0)));
                                accessableField.add(fieldName.toString());
                            }
                        }

                    }
                }
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        if(o.size() > accessableField.size()) {
            return false;
        }

        for(Map.Entry jsonEntry: o.entrySet()) {
            if(false == accessableField.contains(jsonEntry.getKey())) {
                return false;
            }
        }
        return true;
    }

}
