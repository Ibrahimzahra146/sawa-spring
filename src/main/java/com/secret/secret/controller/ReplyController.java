package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.AboutUser;
import com.secret.secret.request.AboutUserModel;
import com.secret.secret.request.CommentModel;
import com.secret.secret.request.ReplyModel;
import com.secret.secret.service.ReactServiceImpl;
import com.secret.secret.service.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	ReplyService replyService;
	  @RequestMapping(value = "api/v1/comment/addNewReply", method = RequestMethod.POST)
	   @ResponseBody
	   public ReplyModel  addNewPost(	
	       @RequestBody ReplyModel replyModel) throws Exception {
		  
		 
		   return replyService.addNewReply(replyModel);
		}

}
