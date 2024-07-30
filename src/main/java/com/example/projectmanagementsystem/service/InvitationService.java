package com.example.projectmanagementsystem.service;

import com.example.projectmanagementsystem.model.Invitation;

public interface InvitationService {
	
	public void senInvitation(String email,Long projectId);
	
	public Invitation acceptInvitation(String token, Long userId);
	
	public String getTokenByUserEmail(String userEmail);
	
	void deleteToken(String token);
	
}
