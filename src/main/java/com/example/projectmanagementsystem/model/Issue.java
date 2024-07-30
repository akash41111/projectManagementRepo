package com.example.projectmanagementsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String title;
	
	private String description;
	
	private String status;
	
	private Long projectID;
	
	private String priority;
	
	private LocalDate dewDate;
	
	private List<String> tagStrings = new ArrayList<>();
	
	
	
	@ManyToOne
	private User assignee;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "issue" , cascade = CascadeType.ALL ,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	private Project project;
}
