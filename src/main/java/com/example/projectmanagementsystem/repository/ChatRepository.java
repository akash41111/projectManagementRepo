package com.example.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectmanagementsystem.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
