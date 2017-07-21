package com.aisino.model;

import java.io.Serializable;

/**
 * Created by gangchaopan on 2017/7/18.
 */
public class ArticleListVO implements Serializable{

    /**
     * 文章id
     */
    private long articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 封面路径
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
     * 作者名称
     */
    private String author;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
