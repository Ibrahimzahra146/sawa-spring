package com.secret.secret.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.secret.secret.model.UserDeviceId;
import com.secret.secret.request.UserDeviceIdModel;

public interface UserDeviceIdService {
public ResponseEntity<UserDeviceId> saveIdWithDeviceId(UserDeviceIdModel userDeviceId);
public int deleteIdWithDeviceId(UserDeviceIdModel userDeviceId);
public List<UserDeviceId> findAllDeviceIdByUserId(int user_id);
}
