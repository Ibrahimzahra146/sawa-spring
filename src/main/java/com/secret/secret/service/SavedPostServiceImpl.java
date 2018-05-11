package com.secret.secret.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Post;
import com.secret.secret.model.SavedPost;
import com.secret.secret.repository.LoginRepository;
import com.secret.secret.repository.PostRepository;
import com.secret.secret.repository.SavedPostRepository;
import com.secret.secret.request.SavedPostModel;

@Service

public class SavedPostServiceImpl implements SavedPostService {
	
	@Autowired
	SavedPostRepository savedPostRep;
	@Autowired
	PostRepository postRep;
	@Autowired
	LoginRepository userRep;
	@Override
	public SavedPostModel savePost(SavedPostModel savedPostModel) {
		SavedPost savedPost=new SavedPost();
		savedPost.setPost(postRep.findPostById(savedPostModel.getPostId()));
		savedPost.setUserId(userRep.findUserById(savedPostModel.getUserId()));
		savedPostRep.save(savedPost);

		// TODO Auto-generated method stub
		return savedPostModel;
	}

	@Override
	public List<Post> getSavedPost(int userId) {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<Post>();
		List<SavedPost> savedPostList = new ArrayList<SavedPost>();
		savedPostList=savedPostRep.findByUserId(userId);
		for(int i=0;i<savedPostList.size();i++){
			postList.add(savedPostList.get(i).getPost());
		}

		return postList;
	}

}
