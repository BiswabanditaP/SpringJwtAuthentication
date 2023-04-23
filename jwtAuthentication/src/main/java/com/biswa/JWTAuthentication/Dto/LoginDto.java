package com.biswa.JWTAuthentication.Dto;

import java.util.Set;



public class LoginDto {
	private String email;
    private String password;
    private String category;
    
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + ", category=" + category + "]";
	}
	
	
	
}
