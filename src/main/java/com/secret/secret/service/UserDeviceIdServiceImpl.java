package com.secret.secret.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secret.secret.model.DeviceToken;
import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.repository.DeviceTokenRepository;
import com.secret.secret.repository.UserDeviceIdRepository;
import com.secret.secret.request.UserDeviceIdModel;

@Service
public class UserDeviceIdServiceImpl implements UserDeviceIdService {
	@Autowired
	UserDeviceIdRepository userDeviceIdRep;
	@Autowired
	LoginService loginService;
	@Autowired
	DeviceTokenService deviceTokenService;
	@Autowired DeviceTokenRepository deviceTokenRep;
	
	
	@Override
	public  ResponseEntity<UserDeviceId>  saveIdWithDeviceId(UserDeviceIdModel userDeviceIdModel) {
		// TODO Auto-generated method stub
		User user=loginService.findUserById(userDeviceIdModel.getUserId());
		DeviceToken device=deviceTokenRep.findDeviceToken( userDeviceIdModel.getDeviceId());
		System.out.println("userDeviceIdModel.getDeviceId()"+userDeviceIdModel.getDeviceId());
		UserDeviceId userDeviceId=userDeviceIdRep.findUserDeviceId(userDeviceIdModel.getUserId(), userDeviceIdModel.getDeviceId());
		if(userDeviceId==null){
			UserDeviceId temp=new UserDeviceId();
			temp.setUserId(user);
			temp.setDeviceId(device);
			userDeviceIdRep.save(temp);
			return new ResponseEntity<>(temp,HttpStatus.OK);
		}
		
		else return new ResponseEntity<>(userDeviceId,HttpStatus.CONFLICT);

	}


	@Override
	public int deleteIdWithDeviceId(UserDeviceIdModel userDeviceId) {
		// TODO Auto-generated method stub
		System.out.println("User Id"+userDeviceId.getUserId());
		System.out.println("Device Id"+userDeviceId.getDeviceId());
		return userDeviceIdRep.deleteUserDeviceId(userDeviceId.getUserId(), userDeviceId.getDeviceId());
	}


	@Override
	public List<UserDeviceId> findAllDeviceIdByUserId(int user_id) {
		// TODO Auto-generated method stub
		return userDeviceIdRep.findAllDeviceIdByUserId(user_id);
	}
}