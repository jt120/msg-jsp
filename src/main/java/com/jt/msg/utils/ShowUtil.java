package com.jt.msg.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * since 2015/4/28.
 */
public class ShowUtil {

    public static String show(String key, HttpServletRequest request) {
        String value = request.getParameter(key);
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return value;
    }
}
