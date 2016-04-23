package me.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by chn on 15/12/15.
 */
@Target(ElementType.FIELD)
public @interface FruitName {

    String value() default "--";
}

