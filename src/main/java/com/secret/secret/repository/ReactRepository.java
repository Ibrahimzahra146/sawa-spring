package com.secret.secret.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Post;
import com.secret.secret.model.React;
import com.secret.secret.model.User;

@Repository
public interface ReactRepository extends JpaRepository<React,Integer> {
	@Query("SELECT r.userId from React r  WHERE (r.post.postId = :id AND type=1)")
	public List<User> findPostLikes(@Param("id") int id);
	@Query("SELECT r.userId from React r  WHERE r.post.postId = :id AND type=2")
	public List<User> findPostDisLikes(@Param("id") int id);
	@Query("SELECT r.userId from React r  WHERE r.post.postId = :id AND type=3")
	public List<User> findPostLoves(@Param("id") int id);
	@Query("SELECT r from React r WHERE (r.post.postId=:postId and r.userId.id=:userId)")
	public React findReactRecord(@Param("postId")int postId,@Param("userId") int userId); 
	@Transactional
	@Modifying
	@Query("DELETE from React r  WHERE r.id = :id")
	public int deleteReactRecordById(@Param("id") int id);
	@Transactional
	@Modifying
	@Query("DELETE from React r  WHERE (r.post.postId=:postId and r.userId.id=:userId)")
	public int deleteReactRecordByUserIdAndPostId(@Param("postId")int postId,@Param("userId") int userId);
}
