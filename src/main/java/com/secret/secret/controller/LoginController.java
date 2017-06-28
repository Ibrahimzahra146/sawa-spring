package com.secret.secret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.secret.secret.model.User;
import com.secret.secret.request.SignUpModel;
import com.secret.secret.request.UserDeviceIdModel;
import com.secret.secret.request.signInModel;
import com.secret.secret.service.LoginService;
import com.secret.secret.service.LoginServiceImpl;
import com.secret.secret.utils.Constants;



@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/api/v1/user/getUser/{id}")
    @ResponseBody
	public User getUser(@PathVariable("id") int id)
	{
		return loginService.findUserById(id);
	}

	@RequestMapping(value="/api/v1/user/signUp",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<User> addNewUser(@RequestBody SignUpModel signUpModel)
	{	
		if(loginService.findUserByEmail(signUpModel.getEmail())==null){
		User user =new User();
		user.setEmail(signUpModel.getEmail());
		user.setFirst_name(signUpModel.getFirst_name());
		user.setLast_name(signUpModel.getLast_name());
		user.setMobile(signUpModel.getMobile());
		user.setPassword(signUpModel.getPassword());
		user.setAnon_post(1);
		user.setBirthdate(null);
		user.setPublic_post_view(1);
		user.setSign_in_out(0);
		user.setImage(Constants.defaultImage);
		User response =loginService.addNewUser(user);
		return new ResponseEntity<User>(response,HttpStatus.OK);

	}
		
	  else{
		  return new ResponseEntity<>(null,HttpStatus.CONFLICT);

	  }
	}
	@RequestMapping(value="/api/v1/user/signIn",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<User> signIn(@RequestBody signInModel model)
	{	
		User user=loginService.verifyEmailAndPassword(model.getEmail(), model.getPassword());

		if(user==null){
		  return new ResponseEntity<>(null,HttpStatus.CONFLICT);

	}
		
	  else{
		  return new ResponseEntity<>(user,HttpStatus.OK);

	  }
	}
	/**
	 * Log out from the application
	 */
	@RequestMapping(value="/api/v1/user/signOut",method=RequestMethod.POST)
    @ResponseBody
	public int signOut(@RequestBody UserDeviceIdModel userDeviceIdModel)
	{	
		return loginService.signOut(userDeviceIdModel);

	
	}
}