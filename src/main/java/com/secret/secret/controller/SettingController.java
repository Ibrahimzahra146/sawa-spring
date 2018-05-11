package com.secret.secret.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.repository.AboutUserRepository;
import com.secret.secret.request.EditEmailModel;
import com.secret.secret.request.EditMobileModel;
import com.secret.secret.request.EditPasswordModel;
import com.secret.secret.request.EditPrivacyModel;
import com.secret.secret.request.SignUpModel;
import com.secret.secret.response.UserDetailsModel;
import com.secret.secret.service.AboutUserService;
import com.secret.secret.service.LoginService;
import com.secret.secret.service.SettingService;

@RestController

public class SettingController {
	@Autowired
	LoginService loginService;
	@Autowired
	SettingService settingService;
	@Autowired
	AboutUserRepository aboutUserRep;
	@RequestMapping(value = "/api/v1/user/settings/edit-email", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> editEmail(@RequestBody EditEmailModel editEmailModel) {
		return settingService.editEmail(editEmailModel);
		
	
	 }
	
	@RequestMapping(value = "/api/v1/user/settings/edit-password", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> editPassword (@RequestBody EditPasswordModel editPasswordModel) {
		return settingService.editPassword(editPasswordModel);
		
	
	 }
	@RequestMapping(value = "/api/v1/user/settings/edit-mobile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> editMobile (@RequestBody EditMobileModel editMobileModel) {
		return settingService.editMobile(editMobileModel);
		
	
	 }
	@RequestMapping(value = "/api/v1/user/settings/edit-privacy", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> editPrivacy (@RequestBody EditPrivacyModel editPrivacyModel) {
		return settingService.editPrivacy(editPrivacyModel);
		
	
	 }
	@RequestMapping(value = "/api/v1/user/settings/set-ative", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserDetailsModel> setActive (@RequestBody EditPrivacyModel editPrivacyModel) {
		return settingService.editPrivacy(editPrivacyModel);
		
	
	 }
	
	

}
