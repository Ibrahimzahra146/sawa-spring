package com.secret.secret.service;

import java.util.List;

import com.secret.secret.model.User;
import com.secret.secret.request.UserDeviceIdModel;

public interface LoginService {
  public User findUserById(int id);
  public User addNewUser(User user);
  public User findUserByEmail(String email);
  public User verifyEmailAndPassword(String email,String password);
  public int signOut(UserDeviceIdModel userDeviceIdModel);
  public int updateSignInOut(int user_id,int flag);
}
