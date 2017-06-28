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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "friend")

public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int id;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "friend1_id")
	
	User friend1_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "friend2_id")
	
	User friend2_id;
	@NotNull
	int state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
}
