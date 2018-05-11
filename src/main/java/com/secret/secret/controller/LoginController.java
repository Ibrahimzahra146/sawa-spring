package com.secret.secret.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.User;
import com.secret.secret.repository.AboutUserRepository;
import com.secret.secret.request.AboutUserModel;
import com.secret.secret.request.EditProfileModel;
import com.secret.secret.request.EmailModel;
import com.secret.secret.request.LoginWithFacebookRequestModel;
import com.secret.secret.request.LoginWithGeneralModel;
import com.secret.secret.request.LoginWithGoogleRequestModel;
import com.secret.secret.request.SignUpModel;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.request.signInModel;
import com.secret.secret.response.UserDetailsModel;
import com.secret.secret.service.AboutUserService;
import com.secret.secret.service.FriendService;
import com.secret.secret.service.LoginService;
import com.secret.secret.service.LoginServiceImpl;
import com.secret.secret.utils.Constants;
import com.secret.secret.utils.GoogleUtil;
import com.secret.secret.utils.LoginWithTypes;

@RestController
public class LoginController {
	LoginWithTypes loginWithTypes = new LoginWithTypes();
	@Autowired
	LoginService loginService;
	@Autowired
	AboutUserService aboutUserService;
	@Autowired
	AboutUserRepository aboutUserRep;
	@Autowired
	FriendService friendService;
	@RequestMapping(value = "/api/v1/user/getUser/{id}")
	@ResponseBody
	public UserDetailsModel getUser(@PathVariable("id") int id) {
		User user = loginService.findUserById(id);
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		if (user != null) {
			userDetailsModel.setUser(user);
			userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(user.getId()));
			userDetailsModel.setNumberOfFollowing(friendService.getNumberOfFollowing(id));
			userDetailsModel.setNumberOfFollower(friendService.getNumberOfFollower(id));
			return userDetailsModel;
		} else
			return null;

	}

	@RequestMapping(value = "/api/v1/user/signUp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> addNewUser(@RequestBody SignUpModel signUpModel) {
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		if (loginService.findUserByEmail(signUpModel.getEmail()) == null) {
			System.out.println("Gender:" + signUpModel.getGender());
			System.out.println("Mobile:" + signUpModel.getMobile());
			System.out.println("Date:" + signUpModel.getBirthdate());

			User user = new User();
			user.setEmail(signUpModel.getEmail());
			user.setFirst_name(signUpModel.getFirst_name());
			user.setLast_name(signUpModel.getLast_name());
			user.setMobile(signUpModel.getMobile());
			user.setPassword(signUpModel.getPassword());
			user.setBirthdate(signUpModel.getBirthdate());
			user.setGender(signUpModel.getGender());
			user.setPublic_post_view(1);
			user.setThemeColor(Constants.PURPLE_THEME_COLOR);
			user.setIsPublic("true");
			user.setIsProfileImagePublic("true");
			user.setSign_in_out(0);
			user.setImage(Constants.defaultImage);
			user.setCover_image(Constants.defaultImage);

			User response = loginService.addNewUser(user);
			AboutUserModel aboutUserModel = new AboutUserModel();

			aboutUserModel.setUserId(response.getId());
			aboutUserModel.setUserBio("");
			aboutUserModel.setUserSong("");
			aboutUserModel.setUserStatus("");
			aboutUserService.addNewAboutUser(aboutUserModel);
			userDetailsModel.setUser(response);
			userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(response.getId()));
			return new ResponseEntity<UserDetailsModel>(userDetailsModel, HttpStatus.OK);

		}

		else {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);

		}
	}

	@RequestMapping(value = "/api/v1/user/login-with-facebook", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> loginWithFacebook(
			@RequestBody LoginWithFacebookRequestModel loginWithFacebookRequestModel) {
		LoginWithGeneralModel loginWithModel = new LoginWithGeneralModel();
		loginWithModel = loginWithFacebookRequestModel;
		
		return loginService.loginWith(loginWithModel, loginWithTypes.FACEBOOK_LOGIN);

	}
	// Login with google

	@RequestMapping(value = "/api/v1/user/login-with-google", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> loginWithGoogle(@RequestBody LoginWithGoogleRequestModel loginWithGoogleRequestModel) {
		LoginWithGeneralModel loginWithModel = new LoginWithGeneralModel();
		loginWithModel = loginWithGoogleRequestModel;
		return loginService.loginWith(loginWithModel, loginWithTypes.GOOGLE_LOGIN);

	}

	@RequestMapping(value = "/api/v1/user/signIn", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> signIn(@RequestBody signInModel model) {
		User user = loginService.verifyEmailAndPassword(model.getEmail());
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		}

		else {

			if (user.getPassword().equals(model.getPassword())) {
				userDetailsModel.setUser(user);
				userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(user.getId()));
				userDetailsModel.setNumberOfFollower(friendService.getNumberOfFollower(user.getId()));
				userDetailsModel.setNumberOfFollowing(friendService.getNumberOfFollowing(user.getId()));

				return new ResponseEntity<>(userDetailsModel, HttpStatus.OK);

			} else
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);

		}
	}

	/**
	 * Log out from the application
	 */
	@RequestMapping(value = "/api/v1/user/signOut", method = RequestMethod.POST)
	@ResponseBody
	public int signOut(@RequestBody UserDeviceIdModel userDeviceIdModel) {
		return loginService.signOut(userDeviceIdModel);

	}

	@RequestMapping(value = "/api/v1/user/editProfile", method = RequestMethod.POST)
	@ResponseBody
	public int editUserProfile(@RequestBody EditProfileModel editProfileModel) {
		int response = loginService.editUserProfile(editProfileModel);

		return response;
	}

	/*
	 * Update passowrd when recoving it
	 * 
	 */
	@RequestMapping(value = "/api/v1/user/update-password", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> UpdatePassword(@RequestBody signInModel signInModel) {

		return loginService.updatePassword(signInModel);

	}
	//Signing up with no registration just using device id
	
	@RequestMapping(value = "/api/v1/user/direct-signUp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> directSignUp(@RequestBody  String  deviceId) {
		UserDetailsModel userDetailsModel = new UserDetailsModel();
//should check if the deviceId already exist
		User temp=loginService.findUserByDeviceId(deviceId);
		if(temp!=null){
			userDetailsModel.setUser(temp);
			userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(temp.getId()));
			return new ResponseEntity<UserDetailsModel>(userDetailsModel, HttpStatus.OK);

			
			
		}else {
			
		
		

			User user = new User();
		
			user.setFirst_name("");
			user.setEmail("");
			user.setLast_name("");
			user.setMobile("");
			user.setPassword("");
			user.setBirthdate(new Date().toString());
			user.setGender("");
			user.setPublic_post_view(1);
			user.setThemeColor(Constants.PURPLE_THEME_COLOR);
			user.setIsPublic("true");
			user.setIsProfileImagePublic("true");
			user.setSign_in_out(1);
			user.setImage("");
			user.setCover_image(Constants.defaultImage);
			user.setDeviceId(deviceId);

			User response = loginService.addNewUser(user);
			AboutUserModel aboutUserModel = new AboutUserModel();

			aboutUserModel.setUserId(response.getId());
			aboutUserModel.setUserBio("");
			aboutUserModel.setUserSong("");
			aboutUserModel.setUserStatus("");
			aboutUserService.addNewAboutUser(aboutUserModel);
			userDetailsModel.setUser(response);
			userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(response.getId()));
			return new ResponseEntity<UserDetailsModel>(userDetailsModel, HttpStatus.OK);
	}
		//}

		///else {
		//	return new ResponseEntity<>(null, HttpStatus.CONFLICT);

		//}
	}
}
