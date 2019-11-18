package com.jaxloveweixin.controller;

import com.jaxloveweixin.common.Constant;
import com.jaxloveweixin.common.MessageUtil;
import com.jaxloveweixin.common.MsgTypeEnum;
import com.jaxloveweixin.entity.frommessage.CheckMessage;
import com.jaxloveweixin.entity.frommessage.WeiXinCheck;
import com.jaxloveweixin.entity.tomessage.Article;
import com.jaxloveweixin.entity.tomessage.NewsMessage;
import com.jaxloveweixin.entity.tomessage.TextMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description 接收weixin的接口，俩个地址相同，一个get用于验证，一个post用于接收用户信息
 *
 * @author wdj on 2018/7/28
 */
@Controller
public class WeiXinApplicationController extends BaseController {

    @ResponseBody
    @GetMapping("weixin")
    public String getToken(CheckMessage checkMessage) {
        boolean checkSignature = WeiXinCheck.checkSignature(checkMessage);
        if (checkSignature) {
            return checkMessage.getEchostr();
        }else {
            return "error";
        }
    }

    @ResponseBody
    @PostMapping("weixin")
    public Object postToken(HttpServletRequest request) throws Exception {
        //从集合中，获取XML各个节点的内容
        Map<String, String> map = MessageUtil.parseXml(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String CreateTime = map.get("CreateTime");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String MsgId = map.get("MsgId");
        if (MsgTypeEnum.TEXT.getType().equals(MsgType)) {
            return testImamge(map);
        } else if (MsgTypeEnum.EVENT.getType().equals(MsgType)) {
            TextMessage message = new TextMessage();
            message.setFromUserName(ToUserName);//原来【接收消息用户】变为回复时【发送消息用户】
            message.setToUserName(FromUserName);
            message.setMsgType("text");
            message.setCreateTime(System.currentTimeMillis());//创建当前时间为消息时间
            if (map.get("EventKey").equals("nothing")) {
                message.setContent("都说了啥都不是了！");
            } else if (map.get("EventKey").equals("abountus")) {
                message.setContent("us:我们；英[əs] 美[ʌs]！");
            } else if (map.get("EventKey").equals("dontkown")) {
                message.setContent("i have no idea what to say！");
            }
            String str = MessageUtil.textMessageToXml(message); //调用Message工具类，将对象转为XML字符串
            return str;
        } else {
            return "2";
        }
//        if(MsgType.equals("text")){//判断消息类型是否是文本消息(text)
//            TextMessage message = new TextMessage();
//            message.setFromUserName(ToUserName);//原来【接收消息用户】变为回复时【发送消息用户】
//            message.setToUserName(FromUserName);
//            message.setMsgType("text");
//            message.setCreateTime(System.currentTimeMillis());//创建当前时间为消息时间
//            message.setContent("您好，"+FromUserName+"\n我是："+ToUserName
//                    +"\n您发送的消息类型为："+MsgType+"\n您发送的时间为"+ DateUtil.format(new Date(Long.parseLong(CreateTime)))
//                    +"\n我回复的时间为："+DateUtil.format(new Date())+"\n您发送的内容是"+Content);
//            str = MessageUtil.textMessageToXml(message); //调用Message工具类，将对象转为XML字符串
//        }
//        System.out.println(str);
//        return str;
    }

    private String testImamge(Map<String, String> requestMap) {
        String respMessage = null;
        try {

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");

                // 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType("news");
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();
                // 单图文消息
                if ("1".equals(content)) {
                    Article article = new Article();
                    article.setTitle("我是一条单图文消息");
                    article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");
                    article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");
                    article.setUrl("www.baidu.com");
                    articleList.add(article);
                    // 设置图文消息个数
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }
                // 多图文消息
                else if ("3".equals(content)) {
                    Article article1 = new Article();
                    article1.setTitle("我是一条多图文消息");
                    article1.setDescription("");
                    article1.setPicUrl("https://bpic.588ku.com/original_pic/19/11/04/a17f9f7cd336ccb864de8e7a70d67489.jpg!/fw/252/quality/99/unsharp/true/compress/true");
                    article1.setUrl("https://588ku.com/png-zt/4160.html");

                    Article article2 = new Article();
                    article2.setTitle("微信公众平台开发教程Java版（二）接口配置 ");
                    article2.setDescription("");
                    article2.setPicUrl("http://imgm3.cnmo-img.com.cn/product_exhibit/15_120x90/643/ceUyv2x381ouI.jpg");
                    article2.setUrl("http://product.cnmo.com/product/1624883_t999.html#p=4");

                    Article article3 = new Article();
                    article3.setTitle("微信公众平台开发教程Java版(三) 消息接收和发送");
                    article3.setDescription("");
                    article3.setPicUrl("http://images-new.123rf.com.cn/450wm/dedivan1923/dedivan19231510/dedivan1923151000679/46784061-pretty-girl-sitting-in-street-with-morning-coffee-and-relaxing.jpg");
                    article3.setUrl("https://www.123rf.com.cn/topic/136/1.html");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                } else {
                    TextMessage message = new TextMessage();
//
                    message.setFromUserName(toUserName);//原来【接收消息用户】变为回复时【发送消息用户】

                    message.setToUserName(fromUserName);

                    message.setMsgType("text");

                    message.setCreateTime(System.currentTimeMillis());//创建当前时间为消息时间

                    message.setContent("你发送的内容是：" + content);
                    respMessage = MessageUtil.textMessageToXml(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(respMessage);
        return respMessage;

    }

}
