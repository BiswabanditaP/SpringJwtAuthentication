package com.biswa.JWTAuthentication.model;

import java.util.Set;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="users",uniqueConstraints= {
		@UniqueConstraint(columnNames= { "username"}
		),
		@UniqueConstraint(columnNames= {"email"})
})
public class User extends Auditable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	private String username;
	private String email;
	private String password;
	private String Address;
	private String longitude;
	private String lattitude;
	private String country;
	private String city;
	private String state;
	private String phone_number;
	private String category;
	private String liscence;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_name() {
		return username;
	}
	public void setUser_name(String user_name) {
		this.username = user_name;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getLiscence() {
		return liscence;
	}
	public void setLiscence(String liscence) {
		this.liscence = liscence;
	}
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	@ManyToMany(fetch = FetchType.LAZY,cascade = { jakarta.persistence.CascadeType.PERSIST })
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public User() {
		
	}
	public User( String user_name, String email, String password, String address, String longitude,
			String lattitude, String country, String city, String state, String phone_number, String category,
			String liscence) {
		super();
		
		this.username = user_name;
		this.email = email;
		this.password = password;
		Address = address;
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.country = country;
		this.city = city;
		this.state = state;
		this.phone_number = phone_number;
		this.category = category;
		this.liscence = liscence;
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + username + ", email=" + email + ", password=" + password
				+ ", Address=" + Address + ", longitude=" + longitude + ", lattitude=" + lattitude + ", country="
				+ country + ", city=" + city + ", state=" + state + ", phone_number=" + phone_number + ", category="
				+ category + ", liscence=" + liscence + "]";
	}
	

}
