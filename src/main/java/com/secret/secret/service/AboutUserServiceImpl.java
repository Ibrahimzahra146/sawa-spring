package com.secret.secret.service;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.AboutUser;
import com.secret.secret.model.User;
import com.secret.secret.repository.AboutUserRepository;
import com.secret.secret.repository.LoginRepository;
import com.secret.secret.request.AboutUserModel;
import com.secret.secret.utils.NotificationUtil;

@Service
public class AboutUserServiceImpl implements AboutUserService {
	@Autowired
	AboutUserRepository aboutUserRep;
	@Autowired
	LoginRepository loginRep;
	@Autowired
	AboutUserService aboutUserService;
	@Override
	public AboutUser addNewAboutUser(AboutUserModel aboutUserModel) {
		// TODO Auto-generated method stub
		System.out.println("aboutUserModel.getUserId()"+aboutUserModel.getUserId());
		User user=loginRep.findUserById(aboutUserModel.getUserId());
		AboutUser aboutUser=new AboutUser();
		aboutUser.setUser(user);
		aboutUser.setUserBio(aboutUserModel.getUserBio());
		aboutUser.setUserStatus(aboutUserModel.getUserStatus());
		aboutUser.setUserSong(aboutUserModel.getUserSong());
		if(aboutUserRep.findAboutUserById(aboutUserModel.getUserId())!=null){
		
			AboutUser  aboutUserId=aboutUserRep.findAboutUserById(aboutUserModel.getUserId());
			int id=aboutUserId.getId();
			aboutUser.setId(id);
		}
		
		return aboutUserRep.save(aboutUser);
	}
	@Override
	public AboutUser findAboutUserById(int userId) {
		// TODO Auto-generated method stub
		return aboutUserRep.findAboutUserById(userId);
	}
	@Override
	public int editAboutUser(AboutUserModel aboutUserModel) {
		NotificationUtil notification=new NotificationUtil();
//		try {
//			//notification.sendPushNotification("");
//		} catch (IOException | JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// TODO Auto-generated method stub
		return aboutUserRep.editABoutUser(aboutUserModel.getUserId(),aboutUserModel.getUserBio(), aboutUserModel.getUserStatus(), aboutUserModel.getUserSong());
	}
	@Override
	public AboutUser editUserSong(AboutUserModel aboutUserModel) throws IOException, JSONException {
		// TODO Auto-generated method stub
		//First we get the about user record and set it in aboutUser Var
		//Then we define new AboutUser object called aboutUser1 to save it
		AboutUser aboutUser=aboutUserService.findAboutUserById(aboutUserModel.getUserId());
		AboutUser aboutUser1=new AboutUser();
		aboutUser1.setId(aboutUser.getId());
		aboutUser1.setUser(aboutUser.getUser());
		aboutUser1.setUserBio(aboutUser.getUserBio());
		aboutUser1.setUserStatus(aboutUser.getUserStatus());
		aboutUser1.setUserSong(aboutUserModel.getUserSong());
		aboutUserRep.save(aboutUser1);
		
		return aboutUserRep.save(aboutUser1);
	}
	

}
