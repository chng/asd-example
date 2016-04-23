package me.util;

/**
 * Created by chn on 16/2/1.
 */
public class Strings {

    public static final String EMPTY = "";

    public static boolean isEmpty(String string) {
        return string==null || string.trim().isEmpty();
    }

    public static boolean isEmpty(StringBuilder string) {
        return string==null || isEmpty(string.toString());
    }

    public static String valueOf(Object obj) {
        return obj==null?"":obj.toString();
    }
}
