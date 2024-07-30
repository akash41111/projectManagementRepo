package com.example.projectmanagementsystem.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.projectmanagementsystem.model.Chat;
import com.example.projectmanagementsystem.model.Project;
import com.example.projectmanagementsystem.model.User;

public interface projectService  {
	
	Project createdProject(Project project, User user) throws Exception;
	
	List<Project> getProjectByTeam(User user,String category,String tag) throws Exception;
	
	
	Project getProjectById(Long projectId) throws Exception;
	
	void deleteProject(Long projectId,Long userId) throws Exception;
	
	Project updatedProject(Project updatedProject, Long id) throws Exception;
	
	void addUserToProject(Long projectId , Long userID) throws Exception;
	
	void removeFromProject(Long projectId , Long userID) throws Exception;
	
	Chat getChatByProjectId(Long projectId)throws Exception;
	
	List<Project> searchprojects(String keyword,User user)throws Exception;
}
