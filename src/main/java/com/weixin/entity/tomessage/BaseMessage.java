package com.weixin.entity.tomessage;

/**
 * 消息基类（公众帐号 -> 用户）
 */
public class BaseMessage {

    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime = System.currentTimeMillis();

    /**
     * 消息类型
     */
    private String MsgType;

    /**
     * FuncFlag设置为1的时候，自动星标刚才接收到的消息，适合活动统计使用
     */
//    private int FuncFlag = 0;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = MessageFormatUtil.formatValue(toUserName);
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = MessageFormatUtil.formatValue(fromUserName);
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = MessageFormatUtil.formatValue(msgType);
    }

//    public int getFuncFlag() {
//        return FuncFlag;
//    }
//
//    public void setFuncFlag(int funcFlag) {
//        FuncFlag = funcFlag;
//    }
}
