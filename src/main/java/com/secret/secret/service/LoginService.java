package com.secret.secret.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.secret.secret.model.User;
import com.secret.secret.request.EditProfileModel;
import com.secret.secret.request.EmailModel;
import com.secret.secret.request.LoginWithFacebookRequestModel;
import com.secret.secret.request.LoginWithGeneralModel;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.request.signInModel;
import com.secret.secret.response.UserDetailsModel;

public interface LoginService {
  public User findUserById(int id);
  public User addNewUser(User user);
  public User findUserByEmail(String email);
  public User verifyEmailAndPassword(String email);
  public int signOut(UserDeviceIdModel userDeviceIdModel);
  public int updateSignInOut(int user_id,int flag);
  public ResponseEntity<UserDetailsModel> loginWith(LoginWithGeneralModel logginWithModel,int source);
  public ResponseEntity<User> updatePassword(signInModel signInModel);
  public User findUserByDeviceId(String deviceId);

  public int editUserProfile(EditProfileModel editProfileModel);
}
