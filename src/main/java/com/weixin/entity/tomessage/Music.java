package com.weixin.entity.tomessage;

/**
 * 音乐消息
 */
public class Music {
    /**
     * 音乐名称
     */
    private String Title;

    /**
     * 音乐描述
     */
    private String Description;

    /**
     * 音乐链接
     */
    private String MusicUrl;

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = MessageFormatUtil.formatValue(title);
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = MessageFormatUtil.formatValue(description);
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = MessageFormatUtil.formatValue(musicUrl);
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String musicUrl) {
        HQMusicUrl = MessageFormatUtil.formatValue(musicUrl);
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = MessageFormatUtil.formatValue(thumbMediaId);
    }
}
