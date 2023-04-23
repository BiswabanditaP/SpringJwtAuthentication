package com.biswa.JWTAuthentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="roles")
@Entity
public class Role {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EnumRoles name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public EnumRoles getName() {
		return name;
	}
	public void setName(EnumRoles name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	

}
