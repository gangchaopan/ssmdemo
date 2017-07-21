package com.aisino.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gangchaopan on 2017/7/18.
 */
public class ArticleDetailVO implements Serializable{


    /**
     * 文章id
     */
    private long articleId;

    /**
     * 文章属类型
     */
    private long styleId;


    /**
     * 静态文章路径
     */
    private String path;

    /**
     * 作者Id
     */
    private long userId;

    /**
     * 文章名称
     */
    private String title;

    /**
     * 文章简介
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章封面
     */
    private String picture;

    /**
     * 浏览人数
     */
    private int viewCount;

    /**
     * 评论人数
     */
    private int commentCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 作者
     */
    private String author;


    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getStyleId() {
        return styleId;
    }

    public void setStyleId(long styleId) {
        this.styleId = styleId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
