package com.secret.secret.response;

import com.secret.secret.model.User;

public class FriendshipResponseModel {
 User friend1;
 User friend2;
public User getFriend1() {
	return friend1;
}
public void setFriend1(User friend1) {
	this.friend1 = friend1;
}
public User getFriend2() {
	return friend2;
}
public void setFriend2(User friend2) {
	this.friend2 = friend2;
}
}
