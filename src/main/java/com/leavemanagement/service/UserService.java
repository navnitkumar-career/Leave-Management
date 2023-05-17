package com.leavemanagement.service;

import com.leavemanagement.model.User;


public interface UserService {

	Boolean register(User user);
	
	Boolean login(User user);
	
	User getUserByEmail(String email);
}
