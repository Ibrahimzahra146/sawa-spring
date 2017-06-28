package com.secret.secret.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Notification;
import com.secret.secret.model.Post;
import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.repository.NotificationRepository;
import com.secret.secret.repository.PostRepository;
import com.secret.secret.request.PostModel;
import com.secret.secret.utils.NotificationUtil;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	LoginService loginService;
	@Autowired
	PostRepository postRep;
	@Autowired 
	FriendService friendService;
	@Autowired 
	NotificationRepository notificationRep;
	@Autowired 
	UserDeviceIdService userDeviceIdService;
	@Autowired
	DeviceTokenService deviceTokenService;
	
	@Override
	public Post addNewPost(PostModel postModel) {
		Post post=new Post();
		User user=loginService.findUserById(postModel.getUserId());
		User toUser=loginService.findUserById(postModel.getToUserId());
		post.setUserId(user);
		post.setToUserId(toUser);
		post.setText(postModel.getText());
		post.setImage(postModel.getImage());
		post.setIsAnon(postModel.getIsAnon());
	    Friend friend=new Friend();
		friend.setFriend1_id(user);
		friend.setFriend2_id(toUser);
		friend.setState(0);
		post=postRep.save(post);
		Notification notification=new Notification();
		notification.setFriend1_id(user);
		notification.setFriend2_id(toUser);
		notification.setFriendshipId(friendService.findFirstFriend());
	
		notification.setPostId(post);
		notification.setType(3);
		notification.setSent_flag(0);
		notification.setRead_flag(0);
		notificationRep.save(notification);
		List<UserDeviceId> userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(postModel.getToUserId());
		String deviceId=userDeviceId.get(0).getDeviceId().getDeviceId();
		String token =deviceTokenService.findDeviceToken(deviceId).getToken();
		NotificationUtil notificationUtil=new NotificationUtil();
		try {
			notificationUtil.sendPushNotification(token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
		
	}
	@Override
	public Post findPostById(int postId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Post findFirstPost(){
		return postRep.findFirstPost();
	}

}
