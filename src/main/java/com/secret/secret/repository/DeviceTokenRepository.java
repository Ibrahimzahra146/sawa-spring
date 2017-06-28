package com.secret.secret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.DeviceToken;
import com.secret.secret.model.User;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken,String>  {
	//Find Token for a device 
	@Query("SELECT d from DeviceToken d WHERE d.deviceId=:deviceId")
	public DeviceToken findDeviceToken(@Param("deviceId")String deviceId);
	
}
