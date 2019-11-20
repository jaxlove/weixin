package com.weixin.entity.tomessage;

/**
 * @author wangdejun
 * @description: 图片属性
 * @date 2019/11/19 16:06
 */
public class Image {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = MessageFormatUtil.formatValue(mediaId);
    }
}
