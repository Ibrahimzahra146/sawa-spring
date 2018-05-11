package com.secret.secret.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	//flag for profilePicture or cover picture
	public String saveUserImage(MultipartFile uploadfile,int userId,int flag);
	public int removeProfileImage(int id);
	public int removeCoverImage(int id);

}
