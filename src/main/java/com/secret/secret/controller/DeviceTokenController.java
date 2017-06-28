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
import com.secret.secret.request.signInModel;
import com.secret.secret.service.DeviceTokenService;

@RestController
public class DeviceTokenController {
	@Autowired
	DeviceTokenService deviceTokenService;
	@RequestMapping(value="/api/v1/deviceToken/fcm",method=RequestMethod.POST)
    @ResponseBody
	public DeviceToken addNewDeviceToken(@RequestBody DeviceToken deviceToken)
	{	
		return deviceTokenService.addNewDeviceToken(deviceToken);
	}
}
