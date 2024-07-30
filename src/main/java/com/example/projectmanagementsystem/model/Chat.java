package com.example.projectmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	private String name;
	
	@OneToOne
	private Project project;
	
	@JsonIgnore
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Message> messages = new ArrayList<>();
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	
}
