package com.secret.secret.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Comment;
import com.secret.secret.model.DeviceToken;
import com.secret.secret.model.Reply;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
	@Query("SELECT c from Comment c WHERE c.post.postId=:postId")
	public List<Comment> findPostComment(@Param("postId")int postId);
	@Query("SELECT c from Comment c WHERE c.id=:commentId")
	public Comment findCommentById(@Param("commentId")int commentId);
	@Query("SELECT DISTINCT  c.post.postId from Comment c WHERE c.post.userId.id=:userId ORDER BY c.seen ASC")
	public List<Integer> findUnSeenComment(@Param("userId")int userId, Pageable pageable);
	@Transactional
	@Modifying
	@Query("UPDATE Comment c set c.seen=true WHERE c.post.postId=:postId ")
	public int setCommentsSeen(@Param("postId")int postId);
	
}
