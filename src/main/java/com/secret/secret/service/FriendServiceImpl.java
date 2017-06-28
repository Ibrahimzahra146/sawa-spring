package com.secret.secret.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Notification;
import com.secret.secret.model.Post;
import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.repository.FriendRepository;
import com.secret.secret.repository.NotificationRepository;
import com.secret.secret.request.FriendshipModel;
import com.secret.secret.utils.NotificationUtil;

@Service
public class FriendServiceImpl implements FriendService {
@Autowired 
FriendRepository friendRep;
@Autowired
LoginService loginService;
@Autowired 
PostService postService;
@Autowired 
UserDeviceIdService userDeviceIdService;
@Autowired DeviceTokenService deviceTokenService;
@Autowired
NotificationRepository notificationRep;
@Autowired
NotificationService notificaitionService;
	@Override
	public Set<Friend> findUserFriends(int id, int state) {
		// TODO Auto-generated method stub
		return friendRep.findUserFriends(id, state);
	}

	@Override
	public int getFriendShipState(int friend1_id, int friend2_id) {
		int state=0;
		Friend friend=friendRep.getFriendShipState(friend1_id, friend2_id);
		if(friend==null){
			state= 3;
		}else if(friend.getState()==0){
			if(friend1_id==friend.getFriend1_id().getId()){
				state= 0;
			}else if(friend.getState()==0){
				if(friend1_id==friend.getFriend2_id().getId()){
					state= 2;
				}
		 }
		}
		else if(friend.getState()==1){
			state= 1;
		}
		return state;
		// TODO Auto-generated method stub
	}

	@Override
	public Friend addNewFriendship(FriendshipModel friendshipModel) {
		// TODO Auto-generated method stub
		User friend1=loginService.findUserById(friendshipModel.getFriend1_id());
		User friend2=loginService.findUserById(friendshipModel.getFriend2_id());
		Friend friend=new Friend();
		friend.setFriend1_id(friend1);
		friend.setFriend2_id(friend2);
		friend.setState(0);
		friend=friendRep.save(friend);
		Notification notification=new Notification();
		notification.setFriend1_id(friend1);
		notification.setFriend2_id(friend2);
		notification.setFriendshipId(friend);
	
		notification.setPostId(postService.findFirstPost());
		notification.setType(3);
		notification.setSent_flag(0);
		notification.setRead_flag(0);
		notificationRep.save(notification);
		List<UserDeviceId> userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(friendshipModel.getFriend2_id());
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

		return friend;
	}

	@Override
	public int deleteFriendship(FriendshipModel friendshipModel) {
		// TODO Auto-generated method stub
		 Friend friend=friendRep.getFriendShipState(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		 int friendshipId=friend.getId();
		 friendRep.deleteFriendship(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		 notificaitionService.deleteFriendshipNotification(friendshipId,3);
		 
		 return  notificaitionService.deleteFriendshipNotification(friendshipId,3);
	}

	@Override
	public int confirmFriendship(FriendshipModel friendshipModel) {
		// TODO Auto-generated method stub
		Friend friend=friendRep.getFriendShipState(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		
		User friend1=loginService.findUserById(friendshipModel.getFriend1_id());
		User friend2=loginService.findUserById(friendshipModel.getFriend2_id());
		int update=friendRep.confirmFriendship(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());		
		Notification notification=new Notification();
		notification.setFriend1_id(friend1);
		notification.setFriend2_id(friend2);
		notification.setFriendshipId(friend);
	
		notification.setPostId(postService.findFirstPost());
		notification.setType(4);
		notification.setSent_flag(0);
		notification.setRead_flag(0);
		notificationRep.save(notification);
		List<UserDeviceId> userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(friendshipModel.getFriend1_id());
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
		return update;
	}

	@Override
	public Friend findFirstFriend() {
		// TODO Auto-generated method stub
		return friendRep.findFirstFriend();
	}
	
}
