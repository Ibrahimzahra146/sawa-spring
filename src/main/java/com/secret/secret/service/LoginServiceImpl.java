package com.secret.secret.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.repository.AboutUserRepository;
import com.secret.secret.repository.LoginRepository;
import com.secret.secret.request.AboutUserModel;
import com.secret.secret.request.EditProfileModel;
import com.secret.secret.request.EmailModel;
import com.secret.secret.request.LoginWithFacebookRequestModel;
import com.secret.secret.request.LoginWithGeneralModel;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.request.signInModel;
import com.secret.secret.response.UserDetailsModel;
import com.secret.secret.utils.Constants;
import com.secret.secret.utils.FacebookUtil;
import com.secret.secret.utils.GoogleUtil;
import com.secret.secret.utils.LoginWithTypes;

@Service
public class LoginServiceImpl implements LoginService {
	LoginWithTypes loginWithTypes = new LoginWithTypes();
	@Autowired
	LoginRepository loginRep;
	@Autowired
	AboutUserRepository aboutUserRep;
	@Autowired
	UserDeviceIdService userDeviceIdService;
	@Autowired
	AboutUserService aboutUserService;
	@Override
	public User findUserById(int id) {
		System.out.println("Id" + id);
		return loginRep.findUserById(id);

	}

	@Override
	public User addNewUser(User user) {
		// TODO Auto-generated method st
		return loginRep.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return loginRep.findUserByEmail(email);
	}

	@Override
	public User verifyEmailAndPassword(String email) {
		// TODO Auto-generated method stub
		return loginRep.verifyEmailAndPassword(email);
	}

	@Override
	public int signOut(UserDeviceIdModel userDeviceIdModel) {
		// TODO Auto-generated method stub
		int signInOutFlag = 1;
		userDeviceIdService.deleteIdWithDeviceId(userDeviceIdModel);
		List<UserDeviceId> userDeviceIdSet = userDeviceIdService.findAllDeviceIdByUserId(userDeviceIdModel.getUserId());
		if (userDeviceIdSet.isEmpty() == true) {
			return loginRep.updateSignInOut(userDeviceIdModel.getUserId(), 0);

		} else
			return loginRep.updateSignInOut(userDeviceIdModel.getUserId(), 1);
	}

	@Override
	public int updateSignInOut(int user_id, int flag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseEntity<UserDetailsModel> loginWith(LoginWithGeneralModel loginWithModel, int source) {
		FacebookUtil facebookUtil = new FacebookUtil();
		GoogleUtil googleUtil = new GoogleUtil();
		UserDetailsModel userDetailsModel=new UserDetailsModel();

		// TODO Auto-generated method stub
		String id = "";
		User user = new User();
		if (source == loginWithTypes.FACEBOOK_LOGIN) {
			try {
				id = facebookUtil.sendGet(loginWithModel.getAccessToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (source == loginWithTypes.GOOGLE_LOGIN) {
			try {
				id = googleUtil.getGoogleUserId(loginWithModel.getAccessToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("loginWithModel.getId("+loginWithModel.getId());
		if (id.equals(loginWithModel.getId()) == true) {
			if (findUserByEmail(loginWithModel.getEmail()) == null) {
				user.setEmail(loginWithModel.getEmail());
				user.setFirst_name(loginWithModel.getFirstName());
				user.setLast_name(loginWithModel.getLastName());
				user.setPassword(loginWithModel.getEmail());
				user.setMobile("12345");
				user.setBirthdate(null);
				user.setPublic_post_view(1);
				user.setThemeColor(Constants.PURPLE_THEME_COLOR);
				user.setIsPublic("true");
				user.setIsProfileImagePublic("true");
				user.setGender(loginWithModel.getGender());
				user.setSign_in_out(0);
				user.setImage(Constants.defaultImage);
				user.setCover_image(Constants.defaultImage);

				User response = addNewUser(user);
				AboutUserModel aboutUserModel = new AboutUserModel();

				aboutUserModel.setUserId(response.getId());
				aboutUserModel.setUserBio("");
				aboutUserModel.setUserSong("");
				aboutUserModel.setUserStatus("");
				aboutUserService.addNewAboutUser(aboutUserModel);
				userDetailsModel.setUser(response);
				userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(response.getId()));
				return new ResponseEntity<UserDetailsModel>(userDetailsModel, HttpStatus.ACCEPTED);
			} else {
				user = findUserByEmail(loginWithModel.getEmail());
				userDetailsModel.setUser(user);
				userDetailsModel.setAboutUser(aboutUserRep.findAboutUserById(user.getId()));
				return new ResponseEntity<UserDetailsModel>(userDetailsModel, HttpStatus.OK);

			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);

		}
	}

	@Override
	public int editUserProfile(EditProfileModel editProfileModel) {
		// TODO Auto-generated method stub
		return loginRep.editUserProfile(editProfileModel.getId(), editProfileModel.getFirst_name(),
				editProfileModel.getLast_name(), editProfileModel.getGender(), editProfileModel.getBirthdate());
	}

	@Override
	public ResponseEntity<User> updatePassword(signInModel signInModel) {
		// TODO Auto-generated method stub
		User user = loginRep.findUserByEmail(signInModel.getEmail());
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.FOUND);
		} else {
			user.setPassword(signInModel.getPassword());
			user = loginRep.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);

		}
	}

	@Override
	public User findUserByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return loginRep.findUserByDeviceId(deviceId);
	}

}
