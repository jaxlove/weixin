package com.jaxloveweixin.common;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author wangdejun
 * @description: SHA1加密工具
 * @date 2019/11/16 15:11
 */
public class SHA1Utils {

    /**
     * @Comment SHA1实现
     * @Author Ron
     * @Date 2017年9月13日 下午3:30:36
     * @return
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String args[]) throws Exception {
        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {"wdjToken", "1573889502", "908380817"};
        //2.对数组进行排序
        Arrays.sort(arr);
        //3.生成字符串
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        System.out.println("SHA后：" + shaEncode(sb.toString()));//201116de3168eee1c3f25533522f141db44bb2b2
    }
}
