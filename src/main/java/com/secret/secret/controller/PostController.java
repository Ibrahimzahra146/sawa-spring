package com.secret.secret.controller;

import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.secret.secret.model.Post;
import com.secret.secret.model.User;
import com.secret.secret.request.PostModel;
import com.secret.secret.request.updateNotificationModel;
import com.secret.secret.response.PostCommentResponse;
import com.secret.secret.response.getNotificationModel;
import com.secret.secret.service.ImageService;
import com.secret.secret.service.NotificationService;
import com.secret.secret.service.PostService;

@RestController
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	ImageService imageService;
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("124MB");
        factory.setMaxRequestSize("124MB");
        return factory.createMultipartConfig();
    }
	   @RequestMapping(value = "api/v1/post/addNewPost", method = RequestMethod.POST)
	   @ResponseBody
	   public Post  addNewPost(	
	       @RequestBody PostModel postModel) throws Exception {
		   System.out.println("User "+postModel.getUserId());
		   System.out.println("is public commnet "+postModel.getIs_public_comment());
		   System.out.println("text  "+postModel.getText());
		   String image="";
		 
		   return postService.addNewPost(postModel, image);
		
	}
	   @RequestMapping(value = "api/v1/post/addNewImagePost", method = RequestMethod.POST)
	   @ResponseBody
	   public Post  uploadFile(
	       @RequestParam(value="uploadfile", required = false) MultipartFile uploadfile,@RequestParam("id") int id,@RequestParam("text") String text,@RequestParam("is_public_comment") boolean is_public_comment) throws Exception {
		   String image="";
		   if(uploadfile!=null){
			    image= imageService.saveUserImage(uploadfile,id,2);

		   }
		   PostModel postModel=new PostModel();
		   postModel.setUserId(id);
		   postModel.setText(text);
		   postModel.setIs_public_comment(is_public_comment);
		   return postService.addNewPost(postModel, image);
		
	}
	   @RequestMapping(value = "/api/v1/post/getUserPost/{id}")
		@ResponseBody
		public List<PostCommentResponse>getUserPost(@PathVariable("id") int id) {
			return postService.getUserPost(id,false);
		}
	   @RequestMapping(value = "/api/v1/post/getUserHomePost/{id}")
	 		@ResponseBody
	 		public  List<PostCommentResponse>getUserHomePost(@PathVariable("id") int id) {
	 			return postService.getUserHomePost(id);
	 		}
	   @RequestMapping(value = "/api/v1/post/getPost/{id}")
		@ResponseBody
		public PostCommentResponse getPost(@PathVariable("id") int id) {
			return postService.getPostWithComments(id);
		}
	   
	   @RequestMapping(value = "/api/v1/post/getRandomPosts/{id}/{page}")
		@ResponseBody
		public  List<PostCommentResponse>getTrendPost(@PathVariable("page") int page) {
		   
			return postService.getRandomPosts(page);
		}
//	@RequestMapping(value="/api/v1/post/addNewPost",method=RequestMethod.POST)
//	public Post addNewPost(@RequestBody PostModel postModel)
//	{
//	  return postService.addNewPost(postModel);
//	}
	  
 
	 
}

