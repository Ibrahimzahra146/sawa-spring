package com.secret.secret.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import com.secret.secret.response.FollowerAndFollowingResponse;
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
	@Autowired
	DeviceTokenService deviceTokenService;
	@Autowired
	NotificationRepository notificationRep;
	@Autowired
	NotificationService notificaitionService;

	@Override
	public Set<Friend> findUserFriends(int id) {
		// TODO Auto-generated method stub
		return friendRep.findUserFriends(id);
	}

	@Override
	public Set<Friend> findUserFriendRequest(int id) {
		// TODO Auto-generated method stub
		//return friendRep.findUserFriendRequest(id);
		return null;
	}

	// flag to indicate weather to get followers or following
	// 0 for followers and 1 for following
	// 2 for requests
	@Override
	public List<User> getFollowing(int id) {
		// TODO Auto-generated method stub
		List<Friend> list = new ArrayList<Friend>();
		list=friendRep.getFollowing(id);
		List<User> result = new ArrayList<User>();
//	
		int i = 0;
		for (i = 0; i < list.size(); i++) {

			User user2 = (User) list.get(i).getFriend2_id();
		
			result.add(user2);

		}

		return result;
	}
	// function to know the position of the user in the action he is the first
	// friend or the second based on the action
	// This is used whem receive follow requests and confirm follow requests or
	// delete follow requests
	// flag used to distinguish between confirm,send or delete follow
	// requests,Since we need to know where are the paramaters
	// that need to be affected

	public int getFriendShipState(int friend1_id, int friend2_id, int flag) {
		int state = 0;
		Friend friend = friendRep.getFollowRecord(friend1_id, friend2_id);
		System.out.println("friend1_id" + friend1_id);
		System.out.println("friend2_id" + friend2_id);

		// not friend
		if (friend == null) {
			state = 3;
		}
			// friend request but see who send the request
//		} else if (friend.getFriend1_id().getId() == friend1_id && friend.getFriend1_state() == 0 && flag == 0) {
//			return 5;
//
//		} else if (friend.getFriend2_id().getId() == friend1_id && friend.getFriend2_state() == 0 && flag == 0) {
//			return 6;
//		} else if (friend.getFriend1_id().getId() == friend1_id && friend.getFriend2_state() == 1 && flag == 1) {
//			return 7;
//		} else if (friend.getFriend2_id().getId() == friend1_id && friend.getFriend1_state() == 1 && flag == 1) {
//			return 8;
//		}
		return state;
		// TODO Auto-generated method stub
	}

	@Override
	public Friend sendNewFollow(FriendshipModel friendshipModel) {
		// TODO Auto-generated method stub
		int friendship_state=1;
		int state=0;
		User friend1 = loginService.findUserById(friendshipModel.getFriend1_id());
		User friend2 = loginService.findUserById(friendshipModel.getFriend2_id());
		//int state = getFriendShipState(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id(), 0);
	//	System.out.println("state" + state);
		if(friend2.getIsPublic().equals("true")){
			friendship_state=2;
		}
		Friend friendShip=friendRep.getFollowRecord(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
	
		if (friendShip == null) {

			Friend friend = new Friend();
			friend.setFriend1_id(friend1);
			friend.setFriend2_id(friend2);
			// state 1 mean first friend send follow request for friend 2 but
			// check if the profile is public
		
			friend.setState(1);
			friend = friendRep.save(friend);
//			Notification notification = new Notification();
//			notification.setFriend1_id(friend1);
//			notification.setFriend2_id(friend2);
//			notification.setFriendshipId(friend);
//
//			notification.setPostId(postService.findFirstPost());
//			notification.setType(3);
//			notification.setSent_flag(0);
//			notification.setRead_flag(0);
//			 notificationRep.save(notification);
//			 List<UserDeviceId> userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(friendshipModel.getFriend2_id());
//			String deviceId=userDeviceId.get(0).getDeviceId().getDeviceId();
//		    String token =deviceTokenService.findDeviceToken(deviceId).getToken();
//			 NotificationUtil notificationUtil=new NotificationUtil();
//			 try {
//			 NotificationUtil.sendPushNotification(token,friend1,3);
//			 } catch (IOException e) {
//			// // TODO Auto-generated catch block
//			 //e.printStackTrace();friendshipModel
//			 } catch (JSONException e) {
//			// // TODO Auto-generated catch block
//			 e.printStackTrace();
//			}
			return friend;

		} else {
			Friend friend = friendRep.getFollowRecord(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());


			return friend;

		}

	}

	@Override
	public int deleteFollow(FriendshipModel friendshipModel) {
		//friend1 who sent the friend request
		// TODO Auto-generated method stub
		Friend friend = friendRep.getFollowRecord(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		if (friend == null) {
			return 0;
		} else {
			friendRep.deleteFollow(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		
			return 1;

		}

	}

	@Override
	public int confirmFollowRequest(FriendshipModel friendshipModel) {
		// // TODO Auto-generated method stub
		int state = getFriendShipState(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id(), 1);
		Friend friend = friendRep.getFollowRecord(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id());
		System.out.println("State :" + state);
//		if (state == 7) {
//			friendRep.updateFollowState(friendshipModel.getFriend1_id(), friendshipModel.getFriend2_id(),
//					friend.getFriend1_state(), 2);
//
//		}
//		if (state == 8) {
//			friendRep.updateFollowState(friendshipModel.getFriend2_id(), friendshipModel.getFriend1_id(), 2,
//					friend.getFriend2_state());
//
//		}

		
		 User  friend1=loginService.findUserById(friendshipModel.getFriend1_id());
		 User friend2=loginService.findUserById(friendshipModel.getFriend2_id());

		 Notification notification=new Notification();
		 notification.setFriend1_id(friend1);
		 notification.setFriend2_id(friend2);
		 notification.setFriendshipId(friend);
		
		 notification.setPostId(postService.findFirstPost());
		 notification.setType(4);
		 notification.setSent_flag(0);
		 notification.setRead_flag(0);
		 notificationRep.save(notification);
		 List<UserDeviceId>
		userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(friendshipModel.getFriend2_id());
		String deviceId=userDeviceId.get(0).getDeviceId().getDeviceId();
		String token=deviceTokenService.findDeviceToken(deviceId).getToken();
		 NotificationUtil notificationUtil=new NotificationUtil();
		 try {
		 notificationUtil.sendPushNotification(token,friend1,4);
		} catch (IOException e) {
		// // TODO Auto-generated catch block
		 e.printStackTrace();
	} catch (JSONException e) {
		// // TODO Auto-generated catch block
		 e.printStackTrace();
		}
		// return update;
		return 1;
	}

	@Override
	public Friend findFirstFriend() {
		// TODO Auto-generated method stub
		return friendRep.findFirstFriend();
	}

	@Override
	public Friend getFollowState(int friend1_id, int friend2_id) {
		int state = 0;
		Friend result =new Friend();
		Friend friend = friendRep.getFollowRecord(friend1_id, friend2_id);
		User user = loginService.findUserById(friend1_id);

		User user1 = loginService.findUserById(friend2_id);
		//Not follow relation
		if (friend == null) {
			result.setFriend1_id(user);
			result.setFriend2_id(user1);
			result.setState(0);
			return result;
		}else 
		
		return friend;

	}

	@Override
	public List<FollowerAndFollowingResponse> getOtherFollowersAndFollowing(int friend_id, int id, int flag) {
		// TODO Auto-generated method stub
		List<Friend> list = new ArrayList<Friend>();
		int i = 0;
		Friend res;
		int temp = 0;
		List<FollowerAndFollowingResponse> result = new ArrayList<FollowerAndFollowingResponse>();
		if (flag == 0) {
			//list = friendRep.getFollowers(friend_id);

		} else if (flag == 1) {
			//list = friendRep.getFollowing(friend_id);
		}

		// select the other users except the user himself

		for (i = 0; i < list.size(); i++) {
			FollowerAndFollowingResponse followerAndFollowingResponse = new FollowerAndFollowingResponse();

			User user = (User) list.get(i).getFriend1_id();
			User user2 = (User) list.get(i).getFriend2_id();
			System.out.println("user :" + user.getId());
			System.out.println("use2r :" + user2.getId());

			if (user.getId() == friend_id) {
				System.out.println("Follower :" + user2.getId());
				temp = user2.getId();
				followerAndFollowingResponse.setUser(user2);

			} else if (user2.getId() == friend_id) {

				temp = user.getId();
				followerAndFollowingResponse.setUser(user);

			}

			res = friendRep.getFollowRecord(id, temp);

			if (res == null) {
				followerAndFollowingResponse.setFriend1_state(0);
			} else {
//				if (res.getFriend1_id().getId() == id) {
//					followerAndFollowingResponse.setFriend1_state(res.getFriend1_state());
//
//				} else if (res.getFriend2_id().getId() == id) {
//					followerAndFollowingResponse.setFriend1_state(res.getFriend2_state());
//				}

			}

			result.add(followerAndFollowingResponse);

		}

		return result;
	}
	@Override

	public int getNumberOfFollower(int id){
		
		return friendRep.getNumberOfFollower(id);
	}
	@Override

	public int getNumberOfFollowing(int id){
		
		return friendRep.getNumberOfFollowing(id);
	}

}

