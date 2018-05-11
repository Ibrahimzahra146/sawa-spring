package com.secret.secret.response;

import java.util.List;

import com.secret.secret.model.Comment;
import com.secret.secret.model.Post;

public class PostCommentResponse {
	Post post;
	List<Comment> comments;
	ReactListModel reacts;
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public ReactListModel getReacts() {
		return reacts;
	}
	public void setReacts(ReactListModel reacts) {
		this.reacts = reacts;
	}
	

}
