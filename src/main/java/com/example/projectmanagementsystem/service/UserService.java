package com.example.projectmanagementsystem.service;

import com.example.projectmanagementsystem.model.User;

public interface UserService {

	User findUserProfileByJwt(String jwt) throws Exception;
	
	User findUserByEmail(String email) throws Exception;
	
	User findUserByID(Long userId) throws Exception;
	
	User updateUserProjectSize(User user,int numbr) throws Exception;
	
	
	
	
}
