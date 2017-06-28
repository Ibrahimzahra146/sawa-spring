package com.secret.secret.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.User;
import com.secret.secret.repository.SearchRepository;
@Service
public class SerachServiceImpl implements SearchService{
	@Autowired
	SearchRepository searchRep;
	@Override
	public Set<User> searchForUser(String word) {
		// TODO Auto-generated method stub
		return searchRep.serachForUser(word);
	}

}
