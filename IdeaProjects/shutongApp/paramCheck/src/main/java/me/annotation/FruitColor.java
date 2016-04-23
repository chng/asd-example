package me.annotation;

/**
 * Created by chn on 15/12/15.
 */
public @interface FruitColor {
    public enum Color {RED, BLUE, TELLOW, GREEN};

    Color fruitColor() default Color.GREEN;
}
