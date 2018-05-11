package com.secret.secret.repository;

import java.util.List;

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
	public Post findPostById(@Param("id") int id);
	@Query("SELECT p from Post p WHERE p.postId=1")
	public Post findFirstPost();
	@Query("SELECT p from Post p WHERE p.userId.id = :id")
	public List<Post> getUserPost(@Param("id") int id);
	@Query("SELECT p from Post p WHERE p.userId.id IN (SELECT f.friend2_id.id from Friend f WHERE (f.friend1_id.id = :id and f.state=1)) ORDER BY p.timestamp DESC")
	public List<Post> getUserHomePost(@Param("id") int id);
//	@Query("SELECT p FROM Post p  ORDER BY p.timestamp DESC Limit =:page")
//	public List<Post> getRandomPosts(@Param("page") int page);
	
}
