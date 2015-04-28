package com.jt.msg.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * since 2015/4/28.
 */
public class DataUtil {

    public static String parse(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String parse(Date date) {
        return parse(date, null);
    }
}
