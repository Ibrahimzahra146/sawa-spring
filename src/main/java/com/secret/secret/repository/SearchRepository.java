package com.secret.secret.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Notification;
import com.secret.secret.model.User;
@Repository
public interface SearchRepository extends JpaRepository<User,Integer> {
	@Query("SELECT u from User u  WHERE CONCAT_WS(' ', first_name,last_name)LIKE %:word%")
	public Set<User> serachForUser(@Param("word") String word);

}
