package com.secret.secret.service;

import java.util.List;
import java.util.Set;

import com.secret.secret.model.Friend;
import com.secret.secret.model.User;
import com.secret.secret.request.FriendshipModel;
import com.secret.secret.response.FriendshipResponseModel;
import com.secret.secret.response.FollowerAndFollowingResponse;
import com.secret.secret.response.getNotificationModel;

public interface FriendService {
	public Set<Friend> findUserFriends(int id);
	public Set<Friend> findUserFriendRequest(int id);

	
	public Friend getFollowState(int friend1_id,int friend2_id);
	
	public Friend sendNewFollow(FriendshipModel friendshipModel);
	public int deleteFollow(FriendshipModel friendshipModel);
	public int confirmFollowRequest(FriendshipModel friendshipModel);
	public int getNumberOfFollower(int id);
	public int getNumberOfFollowing(int id);

	public Friend findFirstFriend();
	//public List<FollowerAndFollowingResponse> getFollowersAndFollowing(int id);
	public List<User> getFollowing(int id);

	public List<FollowerAndFollowingResponse> getOtherFollowersAndFollowing(int friend_id,int id,int flag);
	

}
