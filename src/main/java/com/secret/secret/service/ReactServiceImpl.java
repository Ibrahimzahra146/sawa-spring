package com.secret.secret.service;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Post;
import com.secret.secret.model.React;
import com.secret.secret.model.User;
import com.secret.secret.repository.ReactRepository;
import com.secret.secret.request.ReactModel;
import com.secret.secret.response.ReactSingleModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactServiceImpl implements ReactService {
	@Autowired
	PostService postService;
	@Autowired
	LoginService loginService;
	@Autowired
	ReactRepository reactRep;

	@Override
	public int addNewReact(ReactModel reactModel) {
		// TODO Auto-generated method stub
		React react = new React();
		React temp = new React();
		temp = reactRep.findReactRecord(reactModel.getPostId(), reactModel.getUserId());
		if (temp != null) {

			reactRep.deleteReactRecordById(temp.getId());
		}
		// User user);
		react.setUserId(loginService.findUserById(reactModel.getUserId()));
		react.setType(reactModel.getType());
		react.setPost(postService.findPostById(reactModel.getPostId()));
		reactRep.save(react);
		if (react.getType() == 1) {
			return reactRep.findPostLikes(reactModel.getPostId()).size();
		} else if (react.getType() == 2) {
			return reactRep.findPostDisLikes(reactModel.getPostId()).size();
		} else {
			return reactRep.findPostLoves(reactModel.getPostId()).size();
		}

	}

	@Override
	public int deleteReact(ReactModel reactModel) {
		// React temp=new React();
		// temp=reactRep.findReactRecord(reactModel.getPostId(),reactModel.getUserId());

		return reactRep.deleteReactRecordByUserIdAndPostId(reactModel.getPostId(), reactModel.getUserId());

		// TODO Auto-generated method stub
	}

	@Override
	public ReactSingleModel getPostRect(int postId, int type) {
		ReactSingleModel reactSingleModel = new ReactSingleModel();
		List<User> list = new ArrayList<User>();

		if (type == 1) {
			list = reactRep.findPostLikes(postId);
			reactSingleModel.setUsers(list);
			reactSingleModel.setCount(list.size());
			return reactSingleModel;
		} else if (type == 2) {
			list = reactRep.findPostDisLikes(postId);
			reactSingleModel.setUsers(list);
			reactSingleModel.setCount(list.size());
			return reactSingleModel;
		} else {
			list = reactRep.findPostLoves(postId);
			reactSingleModel.setUsers(list);
			reactSingleModel.setCount(list.size());
			return reactSingleModel;
		}
		// TODO Auto-generated method stub
	}

}
