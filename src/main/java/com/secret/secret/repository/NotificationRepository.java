package com.secret.secret.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Notification;
import com.secret.secret.model.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer>  {
//UPDATE notification n SET read flag=1 WHERE n.id= :id
	@Transactional
	@Modifying
	@Query("UPDATE Notification n SET n.read_flag = 1 WHERE n.id = :id")
	public int udpateReadFlag(@Param("id") int id);
	//Todo  order by time stamp and selsct 10
	@Query("SELECT n from Notification n WHERE n.friend2_id.id=:id and n.sent_flag=1 ORDER BY timestamp DESC")
	public Set<Notification> getSentNotification(@Param("id") int id);
	
	@Query("SELECT n from Notification n WHERE n.friend2_id.id=:id and n.sent_flag=0 ORDER BY timestamp DESC")
	public Set<Notification> getNotSentNotification(@Param("id") int id);
	@Transactional
	@Modifying
	@Query("DELETE FROM Notification n WHERE (n.friendshipId.id=:friendshipId and n.type=:type)")
	public int deleteFriendship(@Param("friendshipId")int friendshipId,@Param("type") int type); 
}
