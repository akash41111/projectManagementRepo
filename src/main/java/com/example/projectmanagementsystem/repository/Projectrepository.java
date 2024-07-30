package com.example.projectmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projectmanagementsystem.model.Project;
import com.example.projectmanagementsystem.model.User;

@Repository
public interface Projectrepository  extends JpaRepository<Project, Long>{

	List<Project> findByOwner(User user);
	
	List<Project> findByNameContainingAndTeamContaining(String partialName,User user);
	
//	@Query("SELECT p From Project p join p.team t where t=:user")
//	List<Project> findProjectByTeam(@Param("User") User user);
	
	List<Project> findProjectByTeamOrOwner(User user,User owner);
}
