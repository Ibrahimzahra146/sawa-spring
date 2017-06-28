package com.secret.secret.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	public void saveUserImage(MultipartFile[] uploadfile,int userId);
}
