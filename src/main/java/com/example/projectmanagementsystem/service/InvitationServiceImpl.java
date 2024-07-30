package com.example.projectmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.example.projectmanagementsystem.model.Invitation;

@Service
public class InvitationServiceImpl implements InvitationService {
	
//	private 

	@Override
	public void senInvitation(String email, Long projectId) {
			
		
	}

	@Override
	public Invitation acceptInvitation(String token, Long userId) {
		
		
		return null;
	}

	@Override
	public String getTokenByUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub
		
	}

}
