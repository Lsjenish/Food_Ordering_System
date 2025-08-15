package com.jenish.Online.Food.Ordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenish.Online.Food.Ordering.config.JwtProvider;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserByJwtTocken(String jwt) throws Exception {
		
		String email  = jwtProvider.getEmailFromJwtTocken(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User findUserByJwtEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		
		if(user == null) throw new Exception("user not found");
		return user;
	}
	
	

}
