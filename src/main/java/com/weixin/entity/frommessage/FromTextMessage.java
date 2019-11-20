package com.weixin.entity.frommessage;

/**
 * 文本消息
 */
public class FromTextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
