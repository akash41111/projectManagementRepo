package com.example.projectmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.example.projectmanagementsystem.model.Chat;
import com.example.projectmanagementsystem.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {
	
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(Chat chat) {
	
		return chatRepository.save(chat);
	}
	
	
	
}
