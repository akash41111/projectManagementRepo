package com.example.projectmanagementsystem.service;



public interface EmailService {
	
	void sendEmailWithToken(String userEmail,String link)throws Exception;
	
}
