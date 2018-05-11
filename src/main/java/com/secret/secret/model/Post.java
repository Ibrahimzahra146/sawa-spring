package com.secret.secret.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int postId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	User userId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "youtubeLink")
	YoutubeLink youtubeLink;

	String text;
	String image;
	String link;
	// @OneToMany(mappedBy = "post")
	// List<React> reacts;
	@NotNull
	boolean isPublicComment;

	public YoutubeLink getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(YoutubeLink youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	
//
//	@CreationTimestamp
//
//	@Column(insertable = true, updatable = false)
//	private Timestamp timestamp;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		this.timestamp = currentTime;
	}

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

	public boolean getIsPublicComment() {
		return isPublicComment;
	}

	public void setIsPublicComment(boolean public_comment) {
		this.isPublicComment = public_comment;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

//	public Timestamp getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(Timestamp timestamp) {
//		this.timestamp = timestamp;
//	}
	
	// public List<React> getReacts() {
	// return reacts;
	// }
	// public void setReacts(List<React> reacts) {
	// this.reacts = reacts;
	// }

}
