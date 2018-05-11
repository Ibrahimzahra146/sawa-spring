package com.secret.secret.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.Reply;

@Repository

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
	

}
