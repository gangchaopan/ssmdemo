

package com.aisino.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体
 * 
 * @author zsy
 * 
 */
public class ArticlePO implements Serializable{

	/**
	 * 文件Id
	 */
	private long articleId;

	/**
	 * 所属目录的第一级Id
	 */
	private long type;

	/**
	 *
	 * 文章页面所在路径
	 */
	private String path;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 文件名称
	 */
	private String title;

	/**
	 * 作者id
	 */
	private long uid;

	/**
	 * 文章简介
	 */
	private String summary;

	/**
	 * 文章内容
	 */
	private String content;

	/**
	 * 封面
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
	 * 文件状态
	 */
	private String  status;

	/**
	 * 审核
	 */
	private String  check;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
}
