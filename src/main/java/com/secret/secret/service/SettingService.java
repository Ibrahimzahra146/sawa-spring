package com.secret.secret.service;

import org.springframework.http.ResponseEntity;

import com.secret.secret.request.EditEmailModel;
import com.secret.secret.request.EditMobileModel;
import com.secret.secret.request.EditPasswordModel;
import com.secret.secret.request.EditPrivacyModel;
import com.secret.secret.request.LoginWithGeneralModel;
import com.secret.secret.response.UserDetailsModel;

public interface SettingService {
	  public ResponseEntity<UserDetailsModel> editEmail(EditEmailModel editEmailModel);
	  public ResponseEntity<UserDetailsModel> editPassword(EditPasswordModel editPasswordModel);
	  public ResponseEntity<UserDetailsModel> editMobile(EditMobileModel editMobileModel);
	  public ResponseEntity<UserDetailsModel> editPrivacy(EditPrivacyModel editPrivacyModel);
	  



}
