package com.secret.secret.service;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Notification;
import com.secret.secret.repository.NotificationRepository;
import com.secret.secret.response.getNotificationModel;

import jnr.ffi.types.sa_family_t;
import scala.annotation.meta.setter;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	NotificationRepository notificationRep;
	@Override
	public int updateReadFlag(int notification_id) {
	
		// TODO Auto-generated method stub
		return notificationRep.udpateReadFlag(notification_id) ;
	} 
	
	@Override
	public getNotificationModel getNotification(int user_id) {
		getNotificationModel notification=new getNotificationModel();
		
		notification.setSent_notification( notificationRep.getSentNotification(user_id));
		notification.setNot_sent_notification(notificationRep.getNotSentNotification(user_id));


		return notification;
	}

	@Override
	public int deleteFriendshipNotification(int friendshipId, int type) {
		// TODO Auto-generated method stub
		return notificationRep.deleteFriendship(friendshipId, type);
	}
	
	
	

}
