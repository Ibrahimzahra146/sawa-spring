package com.secret.secret.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Friend;
import com.secret.secret.model.User;
@Repository
public interface ImageRepository extends JpaRepository<User,Integer>  {
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.image=:image WHERE u.id=:userId ")
	public int updateUserImage(@Param("userId")int userId,@Param("image")String image);
	@Transactional
	@Modifying
	@Query("UPDATE User u set u.cover_image=:image WHERE u.id=:userId ")
	public int updateCoverPic(@Param("userId")int userId,@Param("image")String image);

	

}
