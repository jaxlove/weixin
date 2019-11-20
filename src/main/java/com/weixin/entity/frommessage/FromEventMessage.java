package com.weixin.entity.frommessage;

/**
 * 关注/取消关注事件
 */
public class FromEventMessage extends BaseMessage {
    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)，SCAN（已关注时的事件推送）、
     * LOCATION（用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置）
     * CLICK(用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。)
     * VIEW(点击菜单跳转链接时的事件推送)
     */
    private String Event;

    /**
     * 二维码，事件KEY值，qrscene_为前缀，后面为二维码的参数值
     * 自定义菜单接口中KEY值对应
     * 点击菜单跳转链接时,设置的跳转URL
     */
    private String EventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

    /**
     * 地理位置纬度
     */
    private String Latitude;

    /**
     * 地理位置经度
     */
    private String Longitude;

    /**
     * 地理位置精度
     */
    private String Precision;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
