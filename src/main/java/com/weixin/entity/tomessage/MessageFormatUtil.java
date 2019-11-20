package com.weixin.entity.tomessage;

/**
 * @author wangdejun
 * @description: 消息格式化工具类
 * @date 2019/11/19 16:58
 */
public class MessageFormatUtil {

    public static String formatValue(Object object) {
        if (object != null) {
            return "<![CDATA[" + object.toString() + "]]>";
        } else {
            return null;
        }
    }

}
