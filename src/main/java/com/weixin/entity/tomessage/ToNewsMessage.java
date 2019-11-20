package com.weixin.entity.tomessage;

import java.util.List;

/**
 * 多图文消息，
 * 单图文的时候 Articles 只放一个就行了
 * @author Caspar.chen
 */
public class ToNewsMessage extends BaseMessage {

    /**
     * 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，
     * 开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        this.Articles = articles;
    }
}
