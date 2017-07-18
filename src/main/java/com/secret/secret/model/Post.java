package com.secret.secret.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int postId;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "userId")

User userId;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "toUserId")
	
	User toUserId;
	String text;
	String image;
	@NotNull
	int isAnon;
	
	 @JsonIgnore
	 @CreationTimestamp
	 @Column(insertable = true, updatable = false)
	 private Date timestamp;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public User getToUserId() {
		return toUserId;
	}
	public void setToUserId(User toUserId) {
		this.toUserId = toUserId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getIsAnon() {
		return isAnon;
	}
	public void setIsAnon(int isAnon) {
		this.isAnon = isAnon;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	 
	
}
