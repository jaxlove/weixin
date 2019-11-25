package com.weixin.controller;

import com.weixin.aop.LogDesc;
import com.weixin.weixinutil.ResponseMessageFormatUtil;
import com.weixin.common.SpringContext;
import com.weixin.entity.frommessage.CheckMessage;
import com.weixin.entity.frommessage.WeiXinCheck;
import com.weixin.service.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description 接收weixin的接口
 * 俩个地址相同，一个get用于验证，一个post用于接收用户信息
 * @author wdj on 2018/7/28
 */
@RestController
@RequestMapping("/")
public class WeiXinAcceptController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @LogDesc("微信验证")
    @GetMapping("weixin")
    public String getToken(CheckMessage checkMessage) {
        boolean checkSignature = WeiXinCheck.checkSignature(checkMessage);
        if (checkSignature) {
            return checkMessage.getEchostr();
        } else {
            return "error";
        }
    }

    @PostMapping("weixin")
    public Object postToken(HttpServletRequest request) throws Exception {
        //从集合中，获取XML各个节点的内容
        Map<String, String> map = ResponseMessageFormatUtil.parseXml(request);
        logger.info("接收微信消息 接收参数为：{}",map);
        String MsgType = map.get("MsgType");
        MessageHandler messageHandler = null;
        try {
            messageHandler = SpringContext.getBean(MsgType + "MessageHandler");
        } catch (Exception e) {
            logger.info("获取消息处理器失败：", e.getMessage(), e);
            return "error";
        }
        String handler = messageHandler.handler(map);
        return handler;
    }


}
