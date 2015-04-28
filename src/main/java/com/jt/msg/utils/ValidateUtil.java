package com.jt.msg.utils;

import com.jt.msg.bean.MsgException;
import com.jt.msg.bean.User;
import com.jt.msg.dao.UserDao;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * since 2015/4/28.
 */
public class ValidateUtil {


    public static  boolean notNull(String[] keys, HttpServletRequest request) {
        Map<String, String> errorMap = getErrorMap(request);
        boolean res = true;
        for (String key : keys) {
            String value = request.getParameter(key);
            if (StringUtils.isEmpty(value)) {
                res = false;
                errorMap.put(key, key + "不能为空");
            }
        }
        return res;
    }

    private static Map<String, String> getErrorMap(HttpServletRequest request) {
        Map<String, String> errorMap = (Map<String, String>) request.getAttribute("errorMap");
        if (errorMap == null) {
            errorMap = new HashMap<String, String>();
            request.setAttribute("errorMap", errorMap);
        }
        return errorMap;
    }

    public static boolean login(HttpServletRequest request) {
        UserDao userDao = new UserDao();
        Map<String, String> errorMap = getErrorMap(request);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean res = true;
        password = EncryptUtil.md5(password);
        User login = null;
        try {
            login = userDao.login(username, password);
            session.setAttribute("loginUser", login);
        } catch (MsgException e) {
            errorMap.put("login", e.getMessage());
            res = false;
        }
        return res;
    }

    public static void addError(String key, String msg, HttpServletRequest request) {
        Map<String, String> errorMap = getErrorMap(request);
        errorMap.put(key, msg);
    }

    public static String showErrorMsg(String key, HttpServletRequest request) {
        Map<String, String> errorMap = getErrorMap(request);
        String s = errorMap.get(key);
        if (StringUtils.isEmpty(s)) {
            return "";
        }
        return s;
    }
}
