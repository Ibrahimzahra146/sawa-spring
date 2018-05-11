package com.secret.secret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Reply;
import com.secret.secret.repository.ReplyRepository;
import com.secret.secret.request.ReplyModel;
@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyRepository replyRep;
	@Autowired 
	CommentService commentService;
	@Autowired 
	LoginService loginService;
	
	@Override
	public ReplyModel addNewReply(ReplyModel replyModel) {
		Reply reply=new Reply();
		System.out.println("Comment id"+replyModel.getCommentId());
		System.out.println("id:"+commentService.getCommentById(replyModel.getCommentId()).getId());
		reply.setComment(commentService.getCommentById(replyModel.getCommentId()));
		
		reply.setText(replyModel.getText());
		reply.setUserId(loginService.findUserById(replyModel.getUserId()));
		reply.setTimestamp(reply.getTimestamp());
		replyRep.save(reply);
		// TODO Auto-generated method stub
		return replyModel;
	}

}
