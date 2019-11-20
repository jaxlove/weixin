package com.weixin.service.impl;

import com.weixin.common.MapUtil;
import com.weixin.entity.frommessage.FromTextMessage;
import com.weixin.entity.tomessage.Article;
import com.weixin.service.MessageHandler;
import com.weixin.weixinutil.WeiXinResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangdejun
 * @description: 文本消息处理器
 * @date 2019/11/19 10:52
 */
@Service
public class TextMessageHandler implements MessageHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handler(Map<String, String> receviceMap) {
        String respMessage = null;
        try {
            FromTextMessage receviceMessage = MapUtil.map2Bean(receviceMap, FromTextMessage.class);

            List<Article> articleList = new ArrayList<>();
            // 单图文消息
            if ("1".equals(receviceMessage.getContent())) {
                Article article = new Article();
                article.setTitle("我是一条单图文消息");
                article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");
                article.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/XHh0SksQZPNuhd2BU5yCtd3QflsHz1CF8vV8qYHUPiaaypuYh9jn3t5VNBVjbtplAHIWNkbJ7lQGw0tv9iaQibsEA/640?wx_fmt=jpeg");
                article.setUrl("https://www.cnblogs.com/jaxlove-it");
                articleList.add(article);
                respMessage = WeiXinResponseUtil.newsMessage(receviceMessage, articleList);
            } else {
                respMessage = WeiXinResponseUtil.textMessage(receviceMessage, "你发送的内容是：" + receviceMessage.getContent());
            }
            logger.info("文本消息处理器回复信息：" + respMessage);
        } catch (Exception e) {
            logger.error("文本消息处理器回复异常：", e);
        }
        return respMessage;
    }
}
