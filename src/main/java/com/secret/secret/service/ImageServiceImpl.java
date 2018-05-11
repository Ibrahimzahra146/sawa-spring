package com.secret.secret.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.secret.secret.repository.ImageRepository;
import com.secret.secret.request.ImageModel;
import com.secret.secret.utils.Constants;

@Service

public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRep;
	@Override
	public String saveUserImage(MultipartFile uploadfile,int userId,int flag) {
		
		 String filename="";
		// TODO Auto-generated method stub
	    List<String> files = new ArrayList<>();
	   /* for(int i=0;i<uploadfile.length;i++)
	    {*/
	    	//Get the uploaded file name 
	     String fileName = uploadfile.getOriginalFilename();
	     
	     try {
	       // Get the filename and build the local file path
	      
	       filename = "_"+Math.random()*10000+uploadfile.getOriginalFilename();
	       String directory = "C:/Users/Rabee/Desktop/secret/public/";
	       String filepath = Paths.get(directory, filename).toString();       
	       files.add(filepath);
	       // Save the file locally
	       BufferedOutputStream stream =
	           new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	       stream.write(uploadfile.getBytes());
	       stream.close();
	       System.out.println("userId: "+userId);
	       if(flag==0){
		       imageRep.updateUserImage(userId, filename);

	       }else if(flag==1){
		       imageRep.updateCoverPic(userId, filename);

	       }else if(flag==2){
	    	   
	       }

	     }
	     catch (Exception e) {
	      
	       System.out.println(e.getMessage());

	       
	     }
	     return filename;
	     
	   //}
	}
	@Override
	public int removeProfileImage(int id) {
		return imageRep.updateUserImage(id, Constants.defaultImage);
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public int removeCoverImage(int id) {
		// TODO Auto-generated method stub
		return imageRep.updateCoverPic(id, Constants.defaultImage);
	}

}
