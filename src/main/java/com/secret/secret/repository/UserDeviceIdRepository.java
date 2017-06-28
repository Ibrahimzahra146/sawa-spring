package com.secret.secret.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.secret.secret.model.UserDeviceId;
@Repository
public interface UserDeviceIdRepository extends JpaRepository<UserDeviceId,Integer>  {

		@Query("SELECT u from UserDeviceId u WHERE userId.id=:user_id and deviceId.deviceId=:device_id")	
		public UserDeviceId findUserDeviceId(@Param("user_id")int user_id,@Param("device_id") String device_id);
		
	/*	@Query("INSERT into  UserDeviceId u  (u.userId.id,u.deviceId.deviceId) VALUES (:user_id,:device_id) ")	
		public UserDeviceId addNewUserDeviceId(@Param("user_id")int user_id,@Param("device_id") String device_id);*/
		@Transactional
		@Modifying
		@Query("DELETE FROM UserDeviceId u WHERE userId.id=:user_id and deviceId.deviceId=:device_id")
		public int deleteUserDeviceId(@Param("user_id")int user_id,@Param("device_id") String device_id);
		
		//find all devices that the user signed from
		@Query("SELECT u FROM UserDeviceId u WHERE userId.id=:user_id")
		public List<UserDeviceId> findAllDeviceIdByUserId(@Param("user_id")int user_id);
}
