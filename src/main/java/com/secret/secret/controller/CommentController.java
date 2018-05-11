package com.secret.secret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.secret.secret.model.Reply;

import com.secret.secret.model.Post;
import com.secret.secret.request.CommentModel;
import com.secret.secret.request.PostModel;
import com.secret.secret.response.PostCommentResponse;
import com.secret.secret.response.ReplyResponseModel;
import com.secret.secret.service.CommentService;
import com.secret.secret.service.PostService;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	PostService postService;
	  @RequestMapping(value = "api/v1/comment/addNewComment", method = RequestMethod.POST)
	   @ResponseBody
	   public CommentModel  addNewPost(	
	       @RequestBody CommentModel commentModel) throws Exception {
		  
		 
		   return commentService.addNewComment(commentModel);
		}
	  
	  @RequestMapping(value = "api/v1/comment/getCommentReplies/{commentId}")
	   @ResponseBody
	   public ReplyResponseModel  getCommentReplies(	
			   @PathVariable int commentId) throws Exception {
		  
		 
		   return commentService.getCommentReplies(commentId);
		
	}
	  @RequestMapping(value = "/api/v1/comment/get-recent-comments/{userId}/{page}")
		@ResponseBody
		public List<PostCommentResponse> getRecentComments(@PathVariable("userId") int userId,@PathVariable("page") int page) {
			return commentService.getRecentComments(userId,page);
		}
	  @RequestMapping(value = "api/v1/comment/set-comments-seen/{postId}", method = RequestMethod.POST)
	   @ResponseBody
	   public int  setCommentsSeen(@PathVariable("postId") int postId) throws Exception {
		 
		  commentService.setCommentsSeen(postId);
		  return postId;
		}
	  

}
