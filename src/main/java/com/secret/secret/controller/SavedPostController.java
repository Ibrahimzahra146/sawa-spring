package com.secret.secret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Post;

import com.secret.secret.request.ReactModel;
import com.secret.secret.request.SavedPostModel;
import com.secret.secret.service.SavedPostServiceImpl;

@RestController

public class SavedPostController {
	@Autowired
	SavedPostServiceImpl savedPostServicel;
	@RequestMapping(value = "api/v1/post/save-post", method = RequestMethod.POST)
	  @ResponseBody
	   public SavedPostModel savePost(@RequestBody  SavedPostModel savedPostModel) throws Exception {
	   	 
	   	 
	   	   return savedPostServicel.savePost(savedPostModel);
	   	
	   }
	
	@RequestMapping(value = "/api/v1/post/getSavedPost/{userId}")
	@ResponseBody
	public List<Post> getSavedPost(@PathVariable("userId") int userId) {
		return savedPostServicel.getSavedPost(userId);
	}


}
