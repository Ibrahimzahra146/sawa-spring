package com.secret.secret.service;

import com.secret.secret.model.Post;
import com.secret.secret.request.PostModel;

public interface PostService {
	public Post addNewPost(PostModel postModel);
	public Post findPostById(int postId);
	public Post  findFirstPost();
}
