package com.secret.secret.service;
import java.util.Set;

import com.secret.secret.model.User;
public interface SearchService {
	
	public Set<User> searchForUser(String word);

}
