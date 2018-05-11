package com.secret.secret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secret.secret.model.AboutUser;
import com.secret.secret.model.Post;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.model.YoutubeLink;

@Repository
public interface YoutubeLinkRepository extends JpaRepository<YoutubeLink,Integer> {
	@Query("SELECT y FROM YoutubeLink y WHERE y.id=1")
	public YoutubeLink findYoutubeUrl(@Param("url")String url);
	@Query("SELECT y from YoutubeLink y  WHERE y.link = :link")
	public YoutubeLink findYoutube(@Param("link") String link);
	

}
