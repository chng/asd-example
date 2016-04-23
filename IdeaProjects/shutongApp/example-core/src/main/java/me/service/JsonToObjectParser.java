package me.service;

import me.util.JsonUtil;

/**
 * Created by chn on 16/4/22.
 */
public class JsonToObjectParser {

    Class clazz;

    JsonToObjectParser(Class clazz) {
        this.clazz = clazz;
    }

    public Object parse(String json) {
        if(JsonUtil.canParse(json, clazz)) {
            return JsonUtil.toJavaObject(json, clazz);
        }
        return null;
    }
}
