package com.secret.secret.model;

import java.sql.Date;
import java.time.temporal.Temporal;
import java.util.Calendar;

import javax.annotation.Nullable;
import javax.persistence.Basic;
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
import javax.validation.constraints.Null;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name="notification")

public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "friend1_id")
	User friend1_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "friend2_id")
	User friend2_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=true,name = "postId")
    Post postId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "friendshipId")
    Friend friendshipId;
	
	

	@NotNull
	int type;
	@NotNull
	int read_flag;
	@NotNull
	int sent_flag;
	 @JsonIgnore
	 @CreationTimestamp
	 @Column(insertable = true, updatable = false)
	 private Date timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFriend1_id() {
		return friend1_id;
	}

	public void setFriend1_id(User friend1_id) {
		this.friend1_id = friend1_id;
	}

	public User getFriend2_id() {
		return friend2_id;
	}

	public void setFriend2_id(User friend2_id) {
		this.friend2_id = friend2_id;
	}

	public Post getPostId() {
		return postId;
	}

	public void setPostId(Post postId) {
		this.postId = postId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRead_flag() {
		return read_flag;
	}

	public void setRead_flag(int read_flag) {
		this.read_flag = read_flag;
	}

	public int getSent_flag() {
		return sent_flag;
	}

	public void setSent_flag(int sent_flag) {
		this.sent_flag = sent_flag;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Friend getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(Friend friendshipId) {
		this.friendshipId = friendshipId;
	}


	

}
