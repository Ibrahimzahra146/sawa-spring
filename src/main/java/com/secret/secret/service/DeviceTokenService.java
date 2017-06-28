package com.secret.secret.service;

import com.secret.secret.model.DeviceToken;

public interface DeviceTokenService {
	
 public DeviceToken addNewDeviceToken(DeviceToken deviceToken);
 public DeviceToken findDeviceToken(String deviceId);
}
