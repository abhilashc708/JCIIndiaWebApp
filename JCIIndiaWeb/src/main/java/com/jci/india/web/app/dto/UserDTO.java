package com.jci.india.web.app.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.jci.india.web.app.model.Role;


public class UserDTO {
	
	@NotBlank(message = "name can't be blank")
    @Size(min=3, max = 50)
    private String name;
	
	@NotBlank(message = "User name can't be blank")
	@Size(min=3, max = 50)
    private String username;
	
	 @NotBlank(message = "Email can't be blank")
	 @Size(max = 50)
	 @Email(message = "invalid email format")
     private String email;
	 
    private String password;
	 
    private Set<Role> roles = new HashSet<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
