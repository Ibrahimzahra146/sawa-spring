package com.secret.secret.response;

import java.util.List;

import com.secret.secret.model.User;

public class ReactSingleModel {
	List<User> users;
	int count;
	boolean myAction;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean getMyAction() {
		return myAction;
	}
	public void setMyAction(boolean myAction) {
		this.myAction = myAction;
	}
	


}
