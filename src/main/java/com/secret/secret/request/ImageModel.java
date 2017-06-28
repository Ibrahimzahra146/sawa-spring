package com.secret.secret.request;

import org.springframework.web.multipart.MultipartFile;

public class ImageModel {
	MultipartFile[] uploadfile;
	int userId;
	
	public MultipartFile[] getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile[] uploadfile) {
		this.uploadfile = uploadfile;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
