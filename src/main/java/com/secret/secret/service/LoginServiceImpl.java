package com.secret.secret.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.repository.LoginRepository;
import com.secret.secret.request.UserDeviceIdModel;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired 
	LoginRepository loginRep;
	@Autowired 
	UserDeviceIdService userDeviceIdService;

	@Override
	public User findUserById(int id ) {
		System.out.println("Id"+id);
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
	public User verifyEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return loginRep.verifyEmailAndPassword(email, password);
	}

	@Override
	public int signOut(UserDeviceIdModel userDeviceIdModel) {
		// TODO Auto-generated method stub
		 int signInOutFlag=1;
		 userDeviceIdService.deleteIdWithDeviceId(userDeviceIdModel);
		 List<UserDeviceId> userDeviceIdSet =userDeviceIdService.findAllDeviceIdByUserId(userDeviceIdModel.getUserId());
		 if(userDeviceIdSet.isEmpty()==true){
			 return loginRep.updateSignInOut(userDeviceIdModel.getUserId(), 0);
			 
			 
		 }else 
		 return loginRep.updateSignInOut(userDeviceIdModel.getUserId(), 1);
	}

	@Override
	public int updateSignInOut(int user_id, int flag) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	

}
