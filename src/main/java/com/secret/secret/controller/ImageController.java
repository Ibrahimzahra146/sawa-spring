package com.secret.secret.controller;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.secret.secret.model.User;
import com.secret.secret.service.ImageService;
import com.secret.secret.service.LoginService;



@RestController
public class ImageController {
	@Autowired
	LoginService loginService;
	@Autowired
	ImageService imageService;
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("124MB");
        factory.setMaxRequestSize("124MB");
        return factory.createMultipartConfig();
    }
	   @RequestMapping(value = "api/v1/uploadProfilePic", method = RequestMethod.POST)
	   @ResponseBody
	   public User  uploadFile(
	       @RequestParam("uploadfile") MultipartFile uploadfile,@RequestParam("id") int id) {
		   System.out.println("User "+id);
		  
		   imageService.saveUserImage(uploadfile,id,0);

		   User user=loginService.findUserById(id);
		   return user;
	}
	   //Cover photo
	   @RequestMapping(value = "api/v1/uploadCoverPic", method = RequestMethod.POST)
	   @ResponseBody
	   public User  uploadCoverPic(
	       @RequestParam("uploadfile") MultipartFile uploadfile,@RequestParam("id") int id) {
		   System.out.println("User "+id);
		   imageService.saveUserImage(uploadfile,id,1);

		   User user=loginService.findUserById(id);
		   return user;
	

	   
	}
	   @RequestMapping(value="/api/v1/user/removeImage/{id}/{profileOrCover}")
	    @ResponseBody
		public int remo(@PathVariable("id") int id,@PathVariable("profileOrCover")  int profileOrCover)
		{
		   if(profileOrCover==0){
			   return imageService.removeProfileImage(id);
		   }else if(profileOrCover==1)
			return  imageService.removeCoverImage(id);
		   else return -1;
		}
}
