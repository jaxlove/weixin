package com.weixin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * @author wangdejun
 * @description: Sha1加密工具
 * @date 2019/11/25 10:43
 */
public class Sha1Util {

    public static String decode(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(value.getBytes("UTF-8"));
        String hex = byteToHex(crypt.digest());
        return hex;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
