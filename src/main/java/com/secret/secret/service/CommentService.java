package com.secret.secret.service;

import java.util.List;

import com.secret.secret.model.Comment;
import com.secret.secret.model.Reply;
import com.secret.secret.request.CommentModel;
import com.secret.secret.response.PostCommentResponse;
import com.secret.secret.response.ReplyResponseModel;

public interface CommentService {
	public CommentModel addNewComment(CommentModel commentModel);
	public ReplyResponseModel getCommentReplies(int commentId);
	public List<PostCommentResponse> getRecentComments(int userId,int page);
	public int setCommentsSeen(int postId);
	public Comment getCommentById(int commentId);



}
