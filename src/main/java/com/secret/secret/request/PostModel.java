package com.secret.secret.request;

public class PostModel {
 int userId;
 int toUserId;
 String text;
 String image;
 int isAnon;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getToUserId() {
	return toUserId;
}
public void setToUserId(int toUserId) {
	this.toUserId = toUserId;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getIsAnon() {
	return isAnon;
}
public void setIsAnon(int isAnon) {
	this.isAnon = isAnon;
}
 
}
