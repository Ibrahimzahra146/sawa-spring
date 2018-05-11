package com.secret.secret.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="userDeviceId")
public class UserDeviceId {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int id;

	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userId")
	User userId;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "deviceId")
	DeviceToken deviceId;

	public DeviceToken getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(DeviceToken deviceId) {
		this.deviceId = deviceId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
