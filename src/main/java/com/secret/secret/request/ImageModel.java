package com.secret.secret.request;

import org.springframework.web.multipart.MultipartFile;

public class ImageModel {
	int userId;
	String encodedImage;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEncodedImage() {
		return encodedImage;
	}
	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	

}
