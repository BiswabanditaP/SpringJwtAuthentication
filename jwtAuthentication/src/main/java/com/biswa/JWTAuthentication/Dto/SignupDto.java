package com.biswa.JWTAuthentication.Dto;

import java.util.Set;


public class SignupDto {
	
		private Long id;
		private String user_name;
		private String email;
		private String password;
		private String Address;
		private String longitude;
		private String lattitude;
		private String country;
		private String city;
		private String state;
		private String phone_number;
		private Set<String> roles;
		private String liscence;
		private String category;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
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
		public Set<String> getRoles() {
			return roles;
		}
		public void setRoles(Set<String> roles) {
			this.roles = roles;
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
		@Override
		public String toString() {
			return "SignupDto [id=" + id + ", user_name=" + user_name + ", email=" + email + ", password=" + password
					+ ", Address=" + Address + ", longitude=" + longitude + ", lattitude=" + lattitude + ", country="
					+ country + ", city=" + city + ", state=" + state + ", phone_number=" + phone_number + ", roles="
					+ roles + ", liscence=" + liscence + ", category=" + category + "]";
		}
	  
	
		
	

}
