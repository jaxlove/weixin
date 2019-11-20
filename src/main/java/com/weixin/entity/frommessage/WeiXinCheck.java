package com.weixin.entity.frommessage;

import com.weixin.common.SHA1Utils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * description
 *
 * @ `author wdj on 2018/7/28
 */

public class WeiXinCheck {

    public static final String tooken = "wdjToken"; //开发者自行定义Tooken

    public static boolean checkSignature(CheckMessage checkMessage) {
        if (checkMessage == null || StringUtils.isBlank(checkMessage.getTimestamp()) || StringUtils.isBlank(checkMessage.getNonce())
                || StringUtils.isBlank(checkMessage.getSignature())) {
            return false;
        }
        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {tooken, checkMessage.getTimestamp(), checkMessage.getNonce()};
        //2.对数组进行排序
        Arrays.sort(arr);
        //3.生成字符串
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        //4.sha1加密
        String temp;
        try {
            temp = SHA1Utils.shaEncode(sb.toString());
        } catch (Exception e) {
            return false;
        }
        //5.将加密后的字符串，与微信传来的加密签名比较，返回结果
        return temp.equals(checkMessage.getSignature());
    }

    public static void main(String[] args) {

    }
}
