package com.secret.secret.response;

import com.secret.secret.model.AboutUser;
import com.secret.secret.model.User;

public class UserDetailsModel {
	User user;
	AboutUser aboutUser;
	int numberOfFollower;
	int numberOfFollowing;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AboutUser getAboutUser() {
		return aboutUser;
	}
	public void setAboutUser(AboutUser aboutUser) {
		this.aboutUser = aboutUser;
	}
	public int getNumberOfFollower() {
		return numberOfFollower;
	}
	public void setNumberOfFollower(int numberOfFollower) {
		this.numberOfFollower = numberOfFollower;
	}
	public int getNumberOfFollowing() {
		return numberOfFollowing;
	}
	public void setNumberOfFollowing(int numberOfFollowing) {
		this.numberOfFollowing = numberOfFollowing;
	}
	

}
