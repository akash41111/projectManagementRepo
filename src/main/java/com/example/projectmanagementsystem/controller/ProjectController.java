package com.example.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectmanagementsystem.Response.MessageResponse;
import com.example.projectmanagementsystem.model.Chat;
import com.example.projectmanagementsystem.model.Project;
import com.example.projectmanagementsystem.model.User;
import com.example.projectmanagementsystem.service.UserService;
import com.example.projectmanagementsystem.service.projectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private projectService projectService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		List<Project> projects = projectService.getProjectByTeam(user, category, tag);

		return new ResponseEntity<>(projects, HttpStatus.OK);

	}

	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		Project project = projectService.getProjectById(projectId);

		return new ResponseEntity<>(project, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Project> createproject(@RequestBody Project project,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		Project createdProject = projectService.createdProject(project, user);

		return new ResponseEntity<>(createdProject, HttpStatus.OK);

	}
	
	@PatchMapping("/{projectId}")
	public ResponseEntity<Project> updateProject(@RequestBody Project project,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long projectId
			) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		Project updatedProject = projectService.updatedProject(project, projectId);

		return new ResponseEntity<>(updatedProject, HttpStatus.OK);

	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<MessageResponse> deleteProject(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long projectId
			) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Long userId = user.getId();

		projectService.deleteProject(projectId, userId);
		
		MessageResponse response = new MessageResponse("Delete Successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProject(
			@RequestParam String keyWord,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserProfileByJwt(jwt);

		List<Project> projectsList = projectService.searchprojects(keyWord, user);

		return new ResponseEntity<>(projectsList,HttpStatus.OK);

	}
	
	
	@GetMapping("/{projectId}/chat")
	public ResponseEntity<Chat> getChatByProjectId(
			@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		
		User user = userService.findUserProfileByJwt(jwt);

		Chat chat = projectService.getChatByProjectId(projectId);

		return new ResponseEntity<>(chat, HttpStatus.OK);

	}

	
	

}
