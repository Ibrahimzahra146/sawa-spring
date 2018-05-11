package com.secret.secret.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Post;
import com.secret.secret.model.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
	@Query("SELECT f from Friend f WHERE (f.friend1_id.id = :id OR f.friend2_id.id = :id) AND state=1 ")
	public Set<Friend> findUserFriends(@Param("id") int id);

//	@Query("SELECT f from Friend f WHERE (f.friend2_id.id = :id) AND state=0")
//	public Set<Friend> findUserFriendRequest(@Param("id") int id);

	@Query("SELECT f from Friend f WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id)")
	public Friend getFollowRecord(@Param("friend1_id") int friend1_id, @Param("friend2_id") int friend2_id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Friend f WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id) ")
	public int deleteFollow(@Param("friend1_id") int friend1_id, @Param("friend2_id") int friend2_id);

//	@Transactional
//	@Modifying
//	@Query("UPDATE Friend f set f.state=:state , f.state=:friend2_state WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id) OR (f.friend2_id.id = :friend1_id and f.friend1_id.id = :friend2_id)")
//	public int updateFollowState(@Param("friend1_id") int friend1_id, @Param("friend2_id") int friend2_id,
//			@Param("friend1_state") int friend1_state, @Param("friend2_state") int friend2_state);

	@Query("SELECT f from Friend f WHERE f.friend1_id.id=1 and f.friend2_id.id=1")
	public Friend findFirstFriend();

//	@Query("SELECT f from Friend f WHERE (f.friend2_id.id = :id and f.state=2) OR(f.friend1_id.id = :id and f.state=2) ")
//	public List<Friend> getFollowers(@Param("id") int id);

	@Query("SELECT f from Friend f WHERE (f.friend1_id.id = :id and f.state=1) ")
	public List<Friend> getFollowing(@Param("id") int id);
	
	@Query("SELECT COUNT(f) from Friend f WHERE (f.friend1_id.id = :id and f.state=1) ")
	public int getNumberOfFollowing(@Param("id") int id);

	@Query("SELECT COUNT(f) from Friend f WHERE (f.friend2_id.id = :id and f.state=1) ")
	public int getNumberOfFollower(@Param("id") int id);
//	@Query("SELECT f from Friend f WHERE (f.friend2_id.id = :id and f.state=1) OR(f.friend1_id.id = :id and f.state=1) ")
//	public List<Friend> getFollowRequests(@Param("id") int id);

}
