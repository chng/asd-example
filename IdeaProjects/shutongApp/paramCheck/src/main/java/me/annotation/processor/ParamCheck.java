package me.annotation.processor;

import java.lang.reflect.Field;

/**
 * Created by chn on 15/12/15.
 */
public interface ParamCheck {

    public boolean check(Object value, Field Field);

}
