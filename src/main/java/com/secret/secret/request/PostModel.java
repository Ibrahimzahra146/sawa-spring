package com.secret.secret.request;

public class PostModel {
 int userId;
 String text;
 String link;
 boolean is_public_comment;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}


public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}

public boolean getIs_public_comment() {
	return is_public_comment;
}
public void setIs_public_comment(boolean is_public_comment) {
	this.is_public_comment = is_public_comment;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}


 
}
