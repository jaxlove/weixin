package com.weixin.entity.tomessage;

/**
 * 文本消息
 */
public class ToTextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content =  MessageFormatUtil.formatValue(content);
    }
}
