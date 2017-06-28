package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.Notification;
import com.secret.secret.model.User;
import com.secret.secret.request.updateNotificationModel;
import com.secret.secret.response.getNotificationModel;
import com.secret.secret.service.NotificationService;

@RestController
public class NotificationController {
	@Autowired
	NotificationService notificationService;
	@RequestMapping(value="/api/v1/notification/updateReadFlag",method=RequestMethod.POST)
	public int updateReadFlag(@RequestBody updateNotificationModel notification)
	{
		notificationService.updateReadFlag(notification.getNotification_id());
		return 1 ;
	}
	@RequestMapping(value="/api/v1/notification/getNotification/{user_id}")
	@ResponseBody
	public getNotificationModel getNotification(@PathVariable("user_id") int user_id)
	{
		return notificationService.getNotification(user_id) ;
	}

}
