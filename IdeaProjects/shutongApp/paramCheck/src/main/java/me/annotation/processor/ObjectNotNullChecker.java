package me.annotation.processor;

import java.lang.reflect.Field;

/**
 * Created by chn on 15/12/15.
 */
public class ObjectNotNullChecker implements ParamCheck {
    public boolean check(Object value, Field Field) {
        if(value == null) {
            return false;
        }
        return true;
    }
}
