package com.secret.secret.response;

import com.secret.secret.model.User;

public class FollowerAndFollowingResponse {
	User user;
	int friend1_state;
	int friend2_state;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getFriend1_state() {
		return friend1_state;
	}
	public void setFriend1_state(int friend1_state) {
		this.friend1_state = friend1_state;
	}
	public int getFriend2_state() {
		return friend2_state;
	}
	public void setFriend2_state(int friend2_state) {
		this.friend2_state = friend2_state;
	}
	
	

}
