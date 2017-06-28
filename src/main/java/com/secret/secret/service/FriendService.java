package com.secret.secret.service;

import java.util.Set;

import com.secret.secret.model.Friend;
import com.secret.secret.request.FriendshipModel;
import com.secret.secret.response.FriendshipResponseModel;
import com.secret.secret.response.getNotificationModel;

public interface FriendService {
	public Set<Friend> findUserFriends(int id,int state);
	
	public int getFriendShipState(int friend1_id,int friend2_id);
	
	public Friend addNewFriendship(FriendshipModel friendshipModel);
	public int deleteFriendship(FriendshipModel friendshipModel);
	public int confirmFriendship(FriendshipModel friendshipModel);
	public Friend findFirstFriend();
	

}
