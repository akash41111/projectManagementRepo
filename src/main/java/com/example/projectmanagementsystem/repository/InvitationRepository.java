package com.example.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectmanagementsystem.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
	
	Invitation findByToken(String token);
	
	Invitation findByEmail(String email);
}
