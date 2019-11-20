package com.weixin.weixinutil;

import com.weixin.entity.frommessage.BaseMessage;
import com.weixin.entity.tomessage.Article;
import com.weixin.entity.tomessage.ToNewsMessage;
import com.weixin.entity.tomessage.ToTextMessage;

import java.util.List;

/**
 * @author wangdejun
 * @description: 微信被动回复工具类
 * @date 2019/11/19 15:51
 */
public class WeiXinResponseUtil {

    //文本消息
    public static String textMessage(BaseMessage baseMessage, String content) {
        ToTextMessage toTextMessage = new ToTextMessage();
        toTextMessage.setToUserName(baseMessage.getFromUserName());
        toTextMessage.setFromUserName(baseMessage.getToUserName());
        toTextMessage.setMsgType("text");
        toTextMessage.setContent(content);
        return ResponseMessageFormatUtil.textMessageToXml(toTextMessage);
    }

    //图文消息
    public static String newsMessage(BaseMessage baseMessage, List<Article> articleList) {
        if (articleList != null && !articleList.isEmpty()) {
            ToNewsMessage toNewsMessage = new ToNewsMessage();
            toNewsMessage.setToUserName(baseMessage.getFromUserName());
            toNewsMessage.setFromUserName(baseMessage.getToUserName());
            toNewsMessage.setMsgType("news");
            // 设置图文消息个数
            toNewsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            toNewsMessage.setArticles(articleList);
            return ResponseMessageFormatUtil.newsMessageToXml(toNewsMessage);
        }
        return null;
    }


}
