package me.util;

/**
 * Created by chn on 16/4/25.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by chn on 16/4/22.
 *
 * 修改的JSON转换器. (无成员变量的)对象转换到JSON时带上类名,
 * (其成员变量的值在递归地转换成JSON时,也同样带上类名. not done)
 *
 * 但假设有更好的办法解决对象存储到关系型数据库的问题, 为了能将对象以JSON的形式存储到关系型DB中而改造JSON的标准格式, 代价太大.
 *
 */
@Deprecated
public class DBJsonUtil {

    /**
     * 将一个对象转换成一个Json.
     * <p>
     * 为了方便判断json对应的类的类型, 把类的SimpleName放到JSON串的首部,用空格分割
     *
     * @param o
     * @return
     */
    public static String toJSONString(Object o) {
        if (o == null) {
            return Strings.EMPTY;
        }
        Class clazz = o.getClass();
        if(clazz.isPrimitive()) {
            return clazz.getSimpleName() + " " + JSON.toJSONString(o);
        }

        String json = Strings.EMPTY;
        Map<String, Object> accessableField = Maps.newHashMap();

        for(Class superClazz = clazz;
            superClazz!=Object.class;
            superClazz = superClazz.getSuperclass()) {

            try {
                Field[] fields = superClazz.getDeclaredFields();
                if(fields != null) {
                    for (Field field : fields) {
                        // 如果field是public,加入.
                        if (Modifier.isPublic(field.getModifiers())) {
                            accessableField.put(field.getName(), field.get(o));
                        }
                    }
                }
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        json += clazz.getSimpleName() + " {\n";
        for(Map.Entry entry: accessableField.entrySet()) {
            json += "\""+entry.getKey()+"\":"+toJSONString(entry.getValue())+"\n";
        }
        json += "}";
        return json;
    }


    /**
     * 将一个json转换成clazz类型的对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object toJavaObject(String json, Class<T> clazz) {
        String realJson = json.substring(json.indexOf(' ') + 1);
        return JSON.parseObject(realJson, clazz);
    }

    public static boolean canParse(String json, Class clazz) {
        try {
            String classSimpleName = json.substring(0, json.indexOf(' '));
            return clazz.getSimpleName().equals(classSimpleName);
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }

}
