package com.secret.secret.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.secret.secret.model.User;
import com.secret.secret.request.LoginWithFacebookRequestModel;
import com.secret.secret.request.LoginWithGeneralModel;
import com.secret.secret.request.UserDeviceIdModel;

public interface LoginService {
  public User findUserById(int id);
  public User addNewUser(User user);
  public User findUserByEmail(String email);
  public User verifyEmailAndPassword(String email);
  public int signOut(UserDeviceIdModel userDeviceIdModel);
  public int updateSignInOut(int user_id,int flag);
  public ResponseEntity<User> loginWith(LoginWithGeneralModel logginWithModel,int source);
}
