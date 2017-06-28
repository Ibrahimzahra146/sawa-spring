package com.secret.secret.repository;

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
public interface FriendRepository extends JpaRepository<Friend,Integer>  {
	@Query("SELECT f from Friend f WHERE (f.friend1_id.id = :id OR f.friend2_id.id = :id) AND state=:state ")
	public Set<Friend> findUserFriends(@Param("id") int id,@Param("state") int state);
	
	@Query("SELECT f from Friend f WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id) OR (f.friend2_id.id = :friend1_id and f.friend1_id.id = :friend2_id)")
	public Friend getFriendShipState(@Param("friend1_id")int friend1_id,@Param("friend2_id") int friend2_id);
	@Transactional
	@Modifying
	@Query("DELETE FROM Friend f WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id) OR (f.friend2_id.id = :friend1_id and f.friend1_id.id = :friend2_id)")
	public int deleteFriendship(@Param("friend1_id")int friend1_id,@Param("friend2_id") int friend2_id); 
	@Transactional
	@Modifying
	@Query("UPDATE Friend f set f.state=1 WHERE (f.friend1_id.id = :friend1_id and f.friend2_id.id = :friend2_id) OR (f.friend2_id.id = :friend1_id and f.friend1_id.id = :friend2_id)")
	public int confirmFriendship(@Param("friend1_id")int friend1_id,@Param("friend2_id") int friend2_id);
	@Query("SELECT f from Friend f WHERE f.friend1_id.id=1 and f.friend2_id.id=1")
	public Friend findFirstFriend();
}
