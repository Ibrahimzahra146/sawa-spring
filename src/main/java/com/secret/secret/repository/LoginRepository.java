package com.secret.secret.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.User;
@Repository
public interface LoginRepository extends JpaRepository<User,Integer> 
{
	@Query("SELECT u from User u WHERE u.id = :id")
	public User findUserById(@Param("id") int id);
	
	@Query("SELECT u from User u WHERE u.deviceId = :deviceId")
	public User findUserByDeviceId(@Param("deviceId") String deviceId);
	//find USer by email
	@Query("SELECT u from User u WHERE u.email = :email")
	public User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u from User u WHERE u.email = :email")
	public User verifyEmailAndPassword(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.sign_in_out=:sign_in_out WHERE u.id=:id ")
	public int updateSignInOut(@Param("id")int id,@Param("sign_in_out")int sign_in_out);
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.first_name=:first_name,u.last_name=:last_name,u.gender=:gender,u.birthdate=:birthdate WHERE u.id=:id ")
	public int editUserProfile(@Param("id")int id,@Param("first_name")String first_name,@Param("last_name")String last_name,
			@Param("gender")String gender,@Param("birthdate")String birthdate);
	
}

