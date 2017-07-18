package com.secret.secret.controller;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.secret.secret.service.ImageService;



@RestController
public class ImageController {
	@Autowired
	ImageService imageService;
	//for sick report uploading
	   @RequestMapping(value = "api/v1/uploadFile", method = RequestMethod.POST)
	   @ResponseBody
	   public void  uploadFile(
	       @RequestParam("uploadfile") MultipartFile[] uploadfile) {
		   
		   imageService.saveUserImage(uploadfile,1);
	

	   
	}
}
