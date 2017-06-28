package com.secret.secret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.DeviceToken;
import com.secret.secret.repository.DeviceTokenRepository;

@Service
public class DeviceTokenServiceImpl implements DeviceTokenService {
	@Autowired
	DeviceTokenRepository deviceTokenRep;
	@Override
	public DeviceToken addNewDeviceToken(DeviceToken deviceToken) {
		// TODO Auto-generated method stub
		return deviceTokenRep.save(deviceToken);
	}
	@Override
	public DeviceToken findDeviceToken(String deviceId) {
		// TODO Auto-generated method stub
		return deviceTokenRep.findDeviceToken(deviceId);
	}
	

}
