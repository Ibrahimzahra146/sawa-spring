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

@Service

public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRep;
	@Override
	public void saveUserImage(MultipartFile[] uploadfile,int userId) {
		

		// TODO Auto-generated method stub
	    List<String> files = new ArrayList<>();
	    for(int i=0;i<uploadfile.length;i++)
	    {
	    	//Get the uploaded file name 
	     String fileName = uploadfile[i].getOriginalFilename();
	     
	     try {
	       // Get the filename and build the local file path
	      
	       String filename = "_"+Math.random()*10000+uploadfile[i].getOriginalFilename();
	       String directory = "C:/Users/Rabee/Desktop/secret/public/";
	       String filepath = Paths.get(directory, filename).toString();       
	       files.add(filepath);
	       // Save the file locally
	       BufferedOutputStream stream =
	           new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	       stream.write(uploadfile[i].getBytes());
	       stream.close();
	       System.out.println("userId: "+userId);

	       imageRep.updateUserImage(userId, filename);
	     }
	     catch (Exception e) {
	      
	       System.out.println(e.getMessage());

	       
	     }
	     
	   }
	}

}
