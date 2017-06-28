package com.secret.secret.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.secret.secret.model.User;

import com.secret.secret.response.getNotificationModel;
import com.secret.secret.service.SearchService;

@RestController

public class SearchController {
	@Autowired 
	SearchService searchService;
	@RequestMapping(value="/api/v1/serach/{word}")
	@ResponseBody
	public Set<User> searchForUser(@PathVariable("word") String word)
	{
		return searchService.searchForUser(word);
	}

}
