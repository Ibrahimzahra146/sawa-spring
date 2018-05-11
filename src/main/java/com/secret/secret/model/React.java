package com.secret.secret.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="react")

public class React {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int id;
	@NotNull
	int type;
	@JsonIgnore

	@ManyToOne
	@JoinColumn(name="post",referencedColumnName="postId")
	Post post;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	User userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {	
		this.userId = userId;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
	

}
