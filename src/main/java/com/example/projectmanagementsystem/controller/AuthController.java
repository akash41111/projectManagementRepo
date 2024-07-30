package com.example.projectmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectmanagementsystem.repository.UserRepository;
import com.example.projectmanagementsystem.request.LoginRequest;
import com.example.projectmanagementsystem.service.CustomUserDetailImpl;


import com.example.projectmanagementsystem.Response.AuthResponse;
import com.example.projectmanagementsystem.config.JwtProvider;
import com.example.projectmanagementsystem.model.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private CustomUserDetailImpl customUserDetailImpl;
	
	@PostMapping("/signUp")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		
		User isUserExist = userRepository.findByEmail(user.getEmail());
		
		if(isUserExist != null) {
			throw new Exception("User Already exist with this email");
		}
		
		User createdUser = new User();
		
		
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		
		User savedUser = userRepository.save(createdUser);
		
		
		
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwtString = JwtProvider.generateToken(authentication);
			
			AuthResponse response = new AuthResponse();
			
			response.setJwt(jwtString);
			response.setMsg("signup success");
			
		
//		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
		
		return new ResponseEntity<AuthResponse>(response,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginreq){
		
		String userNameString= loginreq.getEmail();
		String userPassword = loginreq.getPassword();
		
		Authentication authentication = athenticate(userNameString,userPassword);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtString = JwtProvider.generateToken(authentication);
		AuthResponse response = new AuthResponse();
		
		response.setJwt(jwtString);
		response.setMsg("login success");
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}


	private Authentication athenticate(String userName, String userPassword){
		
		UserDetails userDetails  =  customUserDetailImpl.loadUserByUsername(userName);
		
		if(userDetails == null) {
			throw new BadCredentialsException("UserName not valid");
			
		}
		
		if(!passwordEncoder.matches(userPassword,userDetails.getPassword())) {
			throw new BadCredentialsException("Incorrect password");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails , null,userDetails.getAuthorities());
	}
	
	
	
}
