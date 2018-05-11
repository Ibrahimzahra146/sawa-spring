package com.secret.secret.service;
import java.util.List;

import com.secret.secret.model.Post;
import com.secret.secret.request.SavedPostModel;

public interface SavedPostService {
	public SavedPostModel savePost(SavedPostModel savedPost);
	public List<Post> getSavedPost(int userId);


}
