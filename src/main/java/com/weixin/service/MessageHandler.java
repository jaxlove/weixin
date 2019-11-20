package com.weixin.service;

import java.util.Map;

/**
 * 服务端接收消息处理
 */
public interface MessageHandler {

    String handler(Map<String, String> receviceMap);

}
