package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.Post;
import com.secret.secret.request.PostModel;
import com.secret.secret.request.updateNotificationModel;
import com.secret.secret.response.getNotificationModel;
import com.secret.secret.service.NotificationService;
import com.secret.secret.service.PostService;

@RestController
public class PostController {
	@Autowired
	PostService postService;
	@RequestMapping(value="/api/v1/post/addNewPost",method=RequestMethod.POST)
	public Post addNewPost(@RequestBody PostModel postModel)
	{
	  return postService.addNewPost(postModel);
	}
}
