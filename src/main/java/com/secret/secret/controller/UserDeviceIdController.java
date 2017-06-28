package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.DeviceToken;
import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.service.DeviceTokenService;
import com.secret.secret.service.LoginService;
import com.secret.secret.service.UserDeviceIdService;

@RestController
public class UserDeviceIdController {
	@Autowired
	UserDeviceIdService userDeviceIdService;
	@Autowired LoginService loginService;
	@RequestMapping(value="/api/v1/deviceToken/saveIdWithDeviceId",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<UserDeviceId>  saveIdWithDeviceId(@RequestBody UserDeviceIdModel userDeviceIdModel)
	{	
		
	//	UserDeviceId userDeviceId=userDeviceIdService.saveIdWithDeviceId(userDeviceIdModel);
		return userDeviceIdService.saveIdWithDeviceId(userDeviceIdModel);
	}
}
