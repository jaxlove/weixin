package com.weixin.weixinutil;

public enum MsgTypeEnum {

    //普通消息
    TEXT("text"),
    /**
     * 事件推送
     * 1 关注/取消关注事件
     * 2 扫描带参数二维码事件
     * 3 上报地理位置事件
     * 4 自定义菜单事件
     * 5 点击菜单拉取消息时的事件推送
     * 6 点击菜单跳转链接时的事件推送
     */
    EVENT("event"),
    //图片
    IMAGE("image"),
    //语音
    VOICE("voice"),
    //视频
    VIDEO("video"),
    //短视频
    SHORTVIDEO("shortvideo"),
    //位置
    LOCATION("location"),
    //链接
    LINK("link");

    private String type;

    MsgTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
