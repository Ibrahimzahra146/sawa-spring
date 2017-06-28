package com.secret.secret.controller;

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
import com.secret.secret.service.FriendService;

@RestController
public class FriendController {
@Autowired
FriendService friendService;

	@RequestMapping(value="/api/v1/friends/{id}/{state}")
	@ResponseBody
	public Set <Friend> getUserFriends(@PathVariable("id") int id,@PathVariable("state") int state)
	{
		return friendService.findUserFriends(id, state);
	}
	
	@RequestMapping(value="/api/v1/friends/getFriendShipState/{friend1_id}/{friend2_id}")
	@ResponseBody
	public int  getFriendShipState(@PathVariable("friend1_id") int friend1_id,@PathVariable("friend2_id") int friend2_id)
	{
		return friendService.getFriendShipState(friend1_id, friend2_id);
	}
	
	@RequestMapping(value="/api/v1/friend/addNewFriendship",method=RequestMethod.POST)
    @ResponseBody
	public Friend addNewFriendship(@RequestBody FriendshipModel  friendshipModel)
	{	
		return friendService.addNewFriendship(friendshipModel);
	}

	@RequestMapping(value="/api/v1/friend/deleteFriendship",method=RequestMethod.POST)
    @ResponseBody
	public int deleteFriendship(@RequestBody FriendshipModel  friendshipModel)
	{	
		return friendService.deleteFriendship(friendshipModel);
	}

	@RequestMapping(value="/api/v1/friend/confirmFriendship",method=RequestMethod.POST)
    @ResponseBody
	public int confirmFriendship(@RequestBody FriendshipModel  friendshipModel)
	{	
		return friendService.confirmFriendship(friendshipModel);
	}

	
	
}
