package com.secret.secret.response;

import java.util.List;

import com.secret.secret.model.Reply;
import com.secret.secret.model.User;

public class ReplyResponseModel {
	List<Reply> replies;
	User user;
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
