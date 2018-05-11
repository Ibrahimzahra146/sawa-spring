package com.secret.secret.service;

import java.util.List;

import com.secret.secret.model.Post;
import com.secret.secret.model.User;
import com.secret.secret.request.PostModel;
import com.secret.secret.response.PostCommentResponse;

public interface PostService {
	public Post addNewPost(PostModel postModel,String image) throws Exception;
	public Post findPostById(int postId);
	public Post  findFirstPost();
	public List<PostCommentResponse> getUserPost(int id,boolean isPublicComment);
	public  List<PostCommentResponse> getUserHomePost(int id);
	public  List<PostCommentResponse> getRandomPosts(int page);

	public PostCommentResponse getPostWithComments(int postId);




}
