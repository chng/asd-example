package me.annotation;

import java.lang.annotation.*;

/**
 * Created by chn on 15/12/15.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ObjectNotNull {
    //no parameters here
}

