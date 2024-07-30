package com.example.projectmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectmanagementsystem.model.Chat;
import com.example.projectmanagementsystem.model.Project;
import com.example.projectmanagementsystem.model.User;
import com.example.projectmanagementsystem.repository.Projectrepository;

@Service
public class ProjectServiceImpl implements projectService {

	@Autowired
	private Projectrepository projectrepository;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ChatServiceImpl chatService;

	@Override
	public Project createdProject(Project project, User user) throws Exception {

		Project createdProject = new Project();

		createdProject.setOwner(user);
		createdProject.setTags(project.getTags());
		createdProject.setName(project.getName());
		createdProject.setCategory(project.getCategory());
		createdProject.getTeam().add(user);

		Project savedProject = projectrepository.save(createdProject);

		Chat chat = new Chat();

		Chat projectChat = chatService.createChat(chat);

		savedProject.setChat(projectChat);

		return savedProject;
	}

	@Override
	public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {

		List<Project> projects = projectrepository.findProjectByTeamOrOwner(user, user);

		if (category != null) {
			projects = projects.stream().filter(project -> project.getCategory().equals(category))
					.collect(Collectors.toList());

		}

		if (tag != null) {
			projects = projects.stream().filter(project -> project.getTags().contains(tag))
					.collect(Collectors.toList());

		}

		return projects;
	}

	@Override
	public Project getProjectById(Long projectId) throws Exception {

		Optional<Project> optionalProject = projectrepository.findById(projectId);

		if (optionalProject.isEmpty()) {
			throw new Exception("Project Not Found");
		}

		return optionalProject.get();
	}

	@Override
	public void deleteProject(Long projectId, Long userId) throws Exception {

		getProjectById(projectId);

		projectrepository.deleteById(projectId);
	}

	@Override
	public Project updatedProject(Project updatedProject, Long id) throws Exception {

		Project project = getProjectById(id);

		project.setName(updatedProject.getName());
		project.setDescription(updatedProject.getDescription());
		project.setTags(updatedProject.getTags());

		return projectrepository.save(project);

	}

	@Override
	public void addUserToProject(Long projectId, Long userID) throws Exception {

		Project project = getProjectById(projectId);

		User user = userService.findUserByID(userID);

		if (!project.getTeam().contains(user)) {
			project.getChat().getUsers().add(user);
			project.getTeam().add(user);
		}

		projectrepository.save(project);
	}

	@Override
	public void removeFromProject(Long projectId, Long userID) throws Exception {

		Project project = getProjectById(projectId);
		User user = userService.findUserByID(userID);

		if (project.getTeam().contains(user)) {
			project.getChat().getUsers().remove(user);
			project.getTeam().remove(user);
		}

		projectrepository.save(project);

	}

	@Override
	public Chat getChatByProjectId(Long projectId) throws Exception {
		Project project = getProjectById(projectId);
		Chat chat =  project.getChat();	
		return chat;
	}

	@Override
	public List<Project> searchprojects(String keyword, User user) throws Exception {	
		return projectrepository.findByNameContainingAndTeamContaining(keyword, user);
	}

}
