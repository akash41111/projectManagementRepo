package com.example.projectmanagementsystem.Response;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String jwt;
	
	private String msg;
	
}
