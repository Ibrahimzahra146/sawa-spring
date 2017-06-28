package com.secret.secret.service;

import org.springframework.stereotype.Service;

import com.secret.secret.model.Notification;
import com.secret.secret.response.getNotificationModel;

public interface NotificationService {
	
 public int updateReadFlag(int notification_id);
 
 public getNotificationModel getNotification(int user_id);
 public int deleteFriendshipNotification(int friendshipId,int type);

}
