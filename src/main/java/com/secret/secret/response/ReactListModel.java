package com.secret.secret.response;

import java.util.List;

import com.secret.secret.model.Comment;
import com.secret.secret.model.User;

public class ReactListModel {

	ReactSingleModel likeList;
	ReactSingleModel dislikeList;
	ReactSingleModel loveList;
	public ReactSingleModel getLikeList() {
		return likeList;
	}
	public void setLikeList(ReactSingleModel likeList) {
		this.likeList = likeList;
	}
	public ReactSingleModel getDislikeList() {
		return dislikeList;
	}
	public void setDislikeList(ReactSingleModel dislikeList) {
		this.dislikeList = dislikeList;
	}
	public ReactSingleModel getLoveList() {
		return loveList;
	}
	public void setLoveList(ReactSingleModel loveList) {
		this.loveList = loveList;
	}


}
