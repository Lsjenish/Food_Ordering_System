package com.jenish.Online.Food.Ordering.service;

import com.jenish.Online.Food.Ordering.model.User;

public interface UserService {

	public User findUserByJwtTocken(String jwt) throws Exception;
	
	public User findUserByJwtEmail(String email) throws Exception;
}
