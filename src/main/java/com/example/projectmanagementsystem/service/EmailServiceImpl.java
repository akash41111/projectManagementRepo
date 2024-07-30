package com.example.projectmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmailWithToken(String userEmail, String link) throws Exception {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , "utf-8");
		
		String subject = "join Project Team Invitation";
		
		String text = "click the link to join the project team" +link; 
		
		helper.setSubject(subject);
		helper.setText(text);
		
		helper.setTo(userEmail);
		
		javaMailSender.send(mimeMessage);
		
	}

	
	
	
}
