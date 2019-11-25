package com.weixin.service.impl;

import com.weixin.common.GsonTool;
import com.weixin.common.MapUtil;
import com.weixin.entity.frommessage.FromEventMessage;
import com.weixin.service.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangdejun
 * @description: 事件处理器
 * 事件推送
 * 1 关注/取消关注事件
 * 2 扫描带参数二维码事件
 * 3 上报地理位置事件
 * 4 自定义菜单事件
 * 5 点击菜单拉取消息时的事件推送
 * 6 点击菜单跳转链接时的事件推送
 * @date 2019/11/19 11:11
 */
@Service
public class EventMessageHandler implements MessageHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handler(Map<String, String> receviceMap) {
        FromEventMessage fromEventMessage = MapUtil.map2Bean(receviceMap, FromEventMessage.class);
        logger.info("事件推送处理接收消息：" + GsonTool.gsonString(fromEventMessage));

//        List<Article> articleList = new ArrayList<>();
//        Article article1 = new Article();
//        article1.setTitle("我是一条多图文消息");
//        article1.setDescription("");
//        article1.setPicUrl("https://bpic.588ku.com/original_pic/19/11/04/a17f9f7cd336ccb864de8e7a70d67489.jpg!/fw/252/quality/99/unsharp/true/compress/true");
//        article1.setUrl("https://www.cnblogs.com/jaxlove-it");
//
//        articleList.add(article1);
//        String respMessage = WeiXinResponseUtil.newsMessage(fromEventMessage, articleList);
        String respMessage = null;
        return respMessage;
    }
}
