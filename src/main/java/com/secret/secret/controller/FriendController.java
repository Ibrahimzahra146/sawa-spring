package com.secret.secret.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.Friend;
import com.secret.secret.model.User;
import com.secret.secret.request.FriendshipModel;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.response.FriendshipResponseModel;
import com.secret.secret.response.FollowerAndFollowingResponse;
import com.secret.secret.service.FriendService;

@RestController
public class FriendController {
	@Autowired
	FriendService friendService;

	@RequestMapping(value = "/api/v1/friend/getFollowRelationState/{friend1_id}/{friend2_id}")
	@ResponseBody
	public Friend getFollowState(@PathVariable("friend1_id") int friend1_id,
			@PathVariable("friend2_id") int friend2_id) {
		return friendService.getFollowState(friend1_id, friend2_id);
	}

	@RequestMapping(value = "/api/v1/friend/sendNewFollow", method = RequestMethod.POST)
	@ResponseBody	
	public Friend sendNewFollow(@RequestBody FriendshipModel friendshipModel) {
		return friendService.sendNewFollow(friendshipModel);
	}

//	@RequestMapping(value = "/api/v1/friend/confirmFollowRequest", method = RequestMethod.POST)
//	@ResponseBody
//	public int confirmFollowRequest(@RequestBody FriendshipModel friendshipModel) {
//		return friendService.confirmFollowRequest(friendshipModel);
//	}

	@RequestMapping(value = "/api/v1/friend/deleteFollow", method = RequestMethod.POST)
	@ResponseBody
	public int deleteFollow(@RequestBody FriendshipModel friendshipModel) {
		System.out.println("friendshipModel"+friendshipModel.getFriend1_id());
		System.out.println("friendshipModel"+friendshipModel.getFriend2_id());

		return friendService.deleteFollow(friendshipModel);
	}
//
//	@RequestMapping(value = "/api/v1/friend/getFollowers/{id}")
//	@ResponseBody
//	public List<FollowerAndFollowingResponse> getFollowers(@PathVariable("id") int id) {
//		return friendService.getFollowersAndFollowing(id, 0);
//	}
//
	@RequestMapping(value = "/api/v1/friend/getFollowing/{id}")
	@ResponseBody
	public List<User> getFollowing(@PathVariable("id") int id) {
		return friendService.getFollowing(id);
	}
//
//	@RequestMapping(value = "/api/v1/friend/getFollowRequest/{id}")
//	@ResponseBody
//	public List<FollowerAndFollowingResponse> getFollowRequest(@PathVariable("id") int id) {
//		return friendService.getFollowersAndFollowing(id, 2);
//	}

//	@RequestMapping(value = "/api/v1/friend/getOtherFollowers/{friend_id}/{id}")
//	@ResponseBody
//	public List<FollowerAndFollowingResponse> getOtherFollowers(@PathVariable("friend_id") int friend_id,@PathVariable("id") int id) {
//		return friendService.getOtherFollowersAndFollowing(friend_id, id, 0);
//	}
//	@RequestMapping(value = "/api/v1/friend/getOtherFollowing/{friend_id}/{id}")
//	@ResponseBody
//	public List<FollowerAndFollowingResponse> getOtherFollowing(@PathVariable("friend_id") int friend_id,@PathVariable("id") int id) {
//		return friendService.getOtherFollowersAndFollowing(friend_id, id, 1);
//	}
	
}
