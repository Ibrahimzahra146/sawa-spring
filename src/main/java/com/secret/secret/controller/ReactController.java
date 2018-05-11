package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.Post;
import com.secret.secret.model.React;
import com.secret.secret.request.PostModel;
import com.secret.secret.request.ReactModel;
import com.secret.secret.response.ReactSingleModel;
import com.secret.secret.service.PostService;
import com.secret.secret.service.ReactServiceImpl;
@RestController

public class ReactController {
	  
	@Autowired
	ReactServiceImpl reactService;
	@RequestMapping(value = "api/v1/post/addNewReact", method = RequestMethod.POST)
	  @ResponseBody
	   public int  addNewReact(	
	       @RequestBody ReactModel reactModel) throws Exception {
	   	 
	   	 
	   	   return reactService.addNewReact(reactModel);
	   	
	   }
	@RequestMapping(value = "api/v1/post/deleteReact", method = RequestMethod.POST)
	  @ResponseBody
	   public int  deleteReact(	
	       @RequestBody ReactModel reactModel) throws Exception {
	   	 
	   	 
	   	   return reactService.deleteReact(reactModel);
	   	
	   }
	@RequestMapping(value = "api/v1/post/getPostReact/{postId}/{type}", method = RequestMethod.GET)
	  @ResponseBody
	   public ReactSingleModel  getPostReacts(	
			   @PathVariable("postId") int postId,@PathVariable("type") int type) throws Exception {
	   	 
	   	 return reactService.getPostRect(postId, type);
	   	
	   }

}
