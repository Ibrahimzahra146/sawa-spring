package com.secret.secret.controller;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.AboutUser;
import com.secret.secret.model.User;
import com.secret.secret.request.AboutUserModel;
import com.secret.secret.service.AboutUserService;
import com.secret.secret.service.DeviceTokenService;

@RestController
public class AboutUserController {
	@Autowired
	AboutUserService aboutUserService;
	
	@RequestMapping(value="/api/v1/user/addAboutUser",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<AboutUser> addNewAboutUser(@RequestBody AboutUserModel aboutUserModel)
	{	
		AboutUser aboutUser=aboutUserService.addNewAboutUser(aboutUserModel);
		if(aboutUser!=null){
			return new ResponseEntity<AboutUser>(aboutUser,HttpStatus.OK);
		
	}else 
    return new ResponseEntity<AboutUser>(aboutUser,HttpStatus.CONFLICT)	;
 }
	
	@RequestMapping(value="/api/v1/user/editAboutUser",method=RequestMethod.POST)
    @ResponseBody
	public int editAboutUser(@RequestBody AboutUserModel aboutUserModel)
	{	
		try {
				aboutUserService.editAboutUser(aboutUserModel);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
    }
	
	@RequestMapping(value="/api/v1/user/getAbout/{id}")
    @ResponseBody
	public AboutUser getAboutUser(@PathVariable("id") int id)
	{
		return aboutUserService.findAboutUserById(id);
	}
}
