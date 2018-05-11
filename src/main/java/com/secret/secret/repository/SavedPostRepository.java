package com.secret.secret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.secret.secret.model.DeviceToken;
import com.secret.secret.model.Post;
import com.secret.secret.model.SavedPost;

public interface SavedPostRepository extends JpaRepository<SavedPost,Integer> {
	@Query("SELECT s from SavedPost s WHERE s.user.id=:userId")
	public List<SavedPost> findByUserId(@Param("userId")int userId);

}
