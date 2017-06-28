package com.secret.secret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Post;
import com.secret.secret.model.User;

@Repository
public interface PostRepository  extends JpaRepository<Post,Integer>  {
	@Query("SELECT p from Post p WHERE p.postId = :id")
	public User findPostById(@Param("id") int id);
	@Query("SELECT p from Post p WHERE p.postId=1")
	public Post findFirstPost();
}
