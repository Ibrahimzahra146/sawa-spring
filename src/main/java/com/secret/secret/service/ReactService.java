package com.secret.secret.service;

import com.secret.secret.model.Post;
import com.secret.secret.model.React;
import com.secret.secret.request.ReactModel;
import com.secret.secret.response.ReactSingleModel;

public interface ReactService {
	public int addNewReact(ReactModel reactModel);
	public ReactSingleModel getPostRect(int postId,int type);
	public int deleteReact(ReactModel reactModel);


}
