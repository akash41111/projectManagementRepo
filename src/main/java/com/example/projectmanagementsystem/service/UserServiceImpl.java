package com.example.projectmanagementsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectmanagementsystem.config.JwtProvider;
import com.example.projectmanagementsystem.model.User;
import com.example.projectmanagementsystem.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email= JwtProvider.getEmailFromJWTToken(jwt);	
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new Exception("User Not Found");
		}	
		return user;
	}
	
	

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new Exception("User Not Found");
		}	
		return user;
	}
	
	

	@Override
	public User findUserByID(Long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new Exception("User Not Found");
		}
		return user.get();
	}
	
	

	@Override
	public User updateUserProjectSize(User user, int number) throws Exception {
		
		user.setProjectSize(user.getProjectSize()+number);
		
		return userRepository.save(user);
	}

}
