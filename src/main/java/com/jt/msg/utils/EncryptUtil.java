package com.jt.msg.utils;

import org.apache.commons.io.Charsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * since 2015/4/28.
 */
public class EncryptUtil {

    public static String md5(String org) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] source = org.getBytes(Charsets.UTF_8);
            messageDigest.update(source);
            byte[] bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            res = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }
}
