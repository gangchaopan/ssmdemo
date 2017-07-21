package com.aisino.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论信息类
 * Created by Jun on 2015/12/25.
 */
@SuppressWarnings("serial")
public class Comment implements Serializable{

    private int id;  // 评论编号
  
    private int uid; // 评论人
  
  	private int linkId; // 链接文章，图片等id
  
  	private Date createDate; // 创建评论的时间
  
  	private String content;  // 发布的内容

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getLinkId() {
		return linkId;
	}
	
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", uid=" + uid + ", linkId=" + linkId + ", createDate=" + createDate + ", content="
				+ content + "]";
	}
    
  
}
