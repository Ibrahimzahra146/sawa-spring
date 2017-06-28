package com.secret.secret.service;

import java.io.IOException;

import org.json.JSONException;

import com.secret.secret.model.AboutUser;
import com.secret.secret.request.AboutUserModel;

public interface AboutUserService {
	public AboutUser addNewAboutUser(AboutUserModel aboutUserModel);
	public AboutUser findAboutUserById(int userId);
	public int editAboutUser(AboutUserModel aboutUserModel) throws IOException, JSONException;

}
