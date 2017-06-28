package com.secret.secret.response;

import java.util.Set;

import com.secret.secret.model.Notification;

public class getNotificationModel {
	Set<Notification> sent_notification;
	Set<Notification> not_sent_notification;
	public Set<Notification> getSent_notification() {
		return sent_notification;
	}
	public void setSent_notification(Set<Notification> sent_notification) {
		this.sent_notification = sent_notification;
	}
	public Set<Notification> getNot_sent_notification() {
		return not_sent_notification;
	}
	public void setNot_sent_notification(Set<Notification> not_sent_notification) {
		this.not_sent_notification = not_sent_notification;
	}
	

}
