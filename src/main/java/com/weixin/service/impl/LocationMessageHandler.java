package com.weixin.service.impl;

import com.weixin.common.GsonTool;
import com.weixin.service.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangdejun
 * @description: 地理位置处理器
 * @date 2019/11/19 10:52
 */
@Service
public class LocationMessageHandler implements MessageHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handler(Map<String, String> receviceMap) {
        logger.info("地理位置处理器接受消息：", GsonTool.gsonString(receviceMap));
        return "success";
    }
}
