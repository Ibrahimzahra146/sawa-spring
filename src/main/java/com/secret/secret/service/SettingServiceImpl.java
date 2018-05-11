package com.secret.secret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secret.secret.model.User;
import com.secret.secret.repository.AboutUserRepository;
import com.secret.secret.repository.LoginRepository;
import com.secret.secret.request.EditEmailModel;
import com.secret.secret.request.EditMobileModel;
import com.secret.secret.request.EditPasswordModel;
import com.secret.secret.request.EditPrivacyModel;
import com.secret.secret.response.UserDetailsModel;
@Service

public class SettingServiceImpl implements SettingService {
	@Autowired 
	LoginRepository loginRep;
	@Autowired 
	AboutUserRepository aboutUserRep;
	@Override
	public ResponseEntity<UserDetailsModel> editEmail(EditEmailModel editEmailModel) {
		// TODO Auto-generated method stub
		System.out.println("editEmailModel.getPassword()"+editEmailModel.getPassword());
		User user=loginRep.findUserById(editEmailModel.getId());
		if(user.getPassword().equals(editEmailModel.getPassword())){
			user.setEmail(editEmailModel.getNew_email());
			user=loginRep.save(user);
			UserDetailsModel userDeatailsModel=new UserDetailsModel();
			userDeatailsModel.setUser(user);
			userDeatailsModel.setAboutUser(aboutUserRep.findAboutUserById(editEmailModel.getId()));
		    return new ResponseEntity<UserDetailsModel>(userDeatailsModel, HttpStatus.OK);

		}else {
		    return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);

		}
	}
	@Override
	public ResponseEntity<UserDetailsModel> editPassword(EditPasswordModel editPassowrdModel) {
		// TODO Auto-generated method stub
		User user=loginRep.findUserById(editPassowrdModel.getId());
		if(user.getPassword().equals(editPassowrdModel.getOld_password())){
			user.setPassword(editPassowrdModel.getNew_password());
			user=loginRep.save(user);
			UserDetailsModel userDeatailsModel=new UserDetailsModel();
			userDeatailsModel.setUser(user);
			userDeatailsModel.setAboutUser(aboutUserRep.findAboutUserById(editPassowrdModel.getId()));
		    return new ResponseEntity<UserDetailsModel>(userDeatailsModel, HttpStatus.OK);

		}else {
		    return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);

		}
	}
	@Override
	public ResponseEntity<UserDetailsModel> editMobile(EditMobileModel editMobileModel) {
		// TODO Auto-generated method stub
		User user=loginRep.findUserById(editMobileModel.getId());
		if(user.getPassword().equals(editMobileModel.getPassword())){
			user.setMobile(editMobileModel.getNew_mobile());
			user=loginRep.save(user);
			UserDetailsModel userDeatailsModel=new UserDetailsModel();
			userDeatailsModel.setUser(user);
			userDeatailsModel.setAboutUser(aboutUserRep.findAboutUserById(editMobileModel.getId()));
		    return new ResponseEntity<UserDetailsModel>(userDeatailsModel, HttpStatus.OK);

		}else {
		    return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);

		}
	}
	@Override
	public ResponseEntity<UserDetailsModel> editPrivacy(EditPrivacyModel editPrivacyModel) {
		System.out.println("Is public "+editPrivacyModel.getIsProfileImagePublic());
		// TODO Auto-generated method stub
		    User user=loginRep.findUserById(editPrivacyModel.getId());
		    user.setIsPublic(editPrivacyModel.getIsPublic());
		    user.setIsProfileImagePublic(editPrivacyModel.getIsProfileImagePublic());
		    user.setThemeColor(editPrivacyModel.getThemeColor());
			user=loginRep.save(user);
			UserDetailsModel userDeatailsModel=new UserDetailsModel();
			userDeatailsModel.setUser(user);
			userDeatailsModel.setAboutUser(aboutUserRep.findAboutUserById(editPrivacyModel.getId()));
		    return new ResponseEntity<UserDetailsModel>(userDeatailsModel, HttpStatus.OK);

			}

}
