package com.weixin.service.impl;

import com.weixin.common.GsonTool;
import com.weixin.common.MapUtil;
import com.weixin.entity.frommessage.FromEventMessage;
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

        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article();
        article1.setTitle("我是一条多图文消息");
        article1.setDescription("");
        article1.setPicUrl("https://bpic.588ku.com/original_pic/19/11/04/a17f9f7cd336ccb864de8e7a70d67489.jpg!/fw/252/quality/99/unsharp/true/compress/true");
        article1.setUrl("https://www.cnblogs.com/jaxlove-it");

        Article article2 = new Article();
        article2.setTitle("微信公众平台开发教程Java版（二）接口配置 ");
        article2.setDescription("");
        article2.setPicUrl("http://imgm3.cnmo-img.com.cn/product_exhibit/15_120x90/643/ceUyv2x381ouI.jpg");
        article2.setUrl("https://blog.csdn.net/ZQ07506149/article/details/80092021?tdsourcetag=s_pcqq_aiomsg");

        Article article3 = new Article();
        article3.setTitle("微信公众平台开发教程Java版(三) 消息接收和发送");
        article3.setDescription("");
        article3.setPicUrl("http://images-new.123rf.com.cn/450wm/dedivan1923/dedivan19231510/dedivan1923151000679/46784061-pretty-girl-sitting-in-street-with-morning-coffee-and-relaxing.jpg");
        article3.setUrl("https://mp.weixin.qq.com/s?__biz=MzUyOTk5NDQwOA==&mid=2247484594&idx=1&sn=9fd9a0e2dd61f5973eb81349066f8e99&source=41#wechat_redirect");

        articleList.add(article1);
        articleList.add(article2);
        articleList.add(article3);
        String respMessage = WeiXinResponseUtil.newsMessage(fromEventMessage, articleList);
        return respMessage;
    }
}
