package com.secret.secret.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.AboutUser;
import com.secret.secret.model.Friend;

@Repository
public interface AboutUserRepository extends JpaRepository<AboutUser,Integer>  {
	@Query("SELECT a from AboutUser a WHERE a.user.id=:userId")
	public AboutUser findAboutUserById(@Param("userId")int userId);
	
	@Transactional
	@Modifying
	@Query("Update AboutUser a set a.userBio=:userBio,a.userStatus=:userStatus,a.userSong=:userSong WHERE a.user.id=:id")
	public int editABoutUser(@Param("id") int id,@Param("userBio")String userBio,@Param("userStatus")String userStatus,@Param("userSong")String userSong);


}
