package com.secret.secret.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Comment;
import com.secret.secret.model.Post;
import com.secret.secret.model.Reply;
import com.secret.secret.model.User;
import com.secret.secret.repository.CommentRepository;
import com.secret.secret.repository.PostRepository;
import com.secret.secret.request.CommentModel;
import com.secret.secret.response.PostCommentResponse;
import com.secret.secret.response.ReplyResponseModel;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	PostService postService;
	@Autowired
	PostRepository postRep;
	@Autowired
	LoginService logintService;
	@Autowired
	CommentRepository commentRep;
	@Override
	public CommentModel addNewComment(CommentModel commentModel) {
		// TODO Auto-generated method stub
		Comment comment=new Comment();
		Post post=new Post();
		User user=new User();
		post=postService.findPostById(commentModel.getPostId());
		System.out.println("post.getPostId()"+post.getPostId());

		user=logintService.findUserById(commentModel.getUserId());
		comment.setUserId(user);
		comment.setText(commentModel.getText());
		comment.setPost(post);
		comment.setTimestamp();
		//comment.setReplies(replies);
	
		commentRep.save(comment);
		return commentModel;
	}
	@Override
	public ReplyResponseModel getCommentReplies(int commentId) {
		// TODO Auto-generated method stub
		Comment comment=commentRep.findCommentById(commentId);
		ReplyResponseModel replyResponseModel=new ReplyResponseModel();
		replyResponseModel.setReplies(comment.getReplies());
		replyResponseModel.setUser(postService.findPostById(comment.getPost().getPostId()).getUserId());
		return replyResponseModel;
	}
	@Override
	public List<PostCommentResponse> getRecentComments(int userId,int page) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, 5);

		List<Integer> postIdsList=commentRep.findUnSeenComment(userId,pageable);
		List<PostCommentResponse> response= new ArrayList<PostCommentResponse>();
		for(int i=0;i<postIdsList.size();i++){
			PostCommentResponse temp=new PostCommentResponse();
			temp.setPost(postRep.findPostById(postIdsList.get(i)));
			temp.setComments(commentRep.findPostComment(postIdsList.get(i)));
			temp.setReacts(null); 
			response.add(temp);
			
		}
		return response;
	}
	@Override
	public int setCommentsSeen(int postId) {
		// TODO Auto-generated method stub
		commentRep.setCommentsSeen(postId);
		
		return 0;
	}
	@Override
	public Comment getCommentById(int commentId) {
		// TODO Auto-generated method stub
		return commentRep.findCommentById(commentId);
	}
	

}
