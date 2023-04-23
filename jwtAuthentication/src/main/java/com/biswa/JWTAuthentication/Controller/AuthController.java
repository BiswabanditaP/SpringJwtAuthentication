package com.biswa.JWTAuthentication.Controller;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biswa.JWTAuthentication.Dto.LoginDto;

import com.biswa.JWTAuthentication.Dto.SignupDto;

import com.biswa.JWTAuthentication.model.EnumRoles;

import com.biswa.JWTAuthentication.model.Role;
import com.biswa.JWTAuthentication.model.User;

import com.biswa.JWTAuthentication.repository.RoleRepository;
import com.biswa.JWTAuthentication.repository.UserRepository;
import com.biswa.JWTAuthentication.response.StatusMessages;
import com.biswa.JWTAuthentication.security.jwt.JwtUtils;
import com.biswa.JWTAuthentication.services.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	

	
	


	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {

		System.out.println("hi inside login controller");

		Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

		Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
		System.out.println(user);
		List<User> user2 = user.stream().collect(Collectors.toList());
		System.out.println(user2);
		for (User userdetails : user2) {
			System.out.println(userdetails.getCategory());
			System.out.println(loginDto.getCategory());
			System.out.println("inside for loop");
			if ((loginDto.getCategory().trim().length()>0)&&(!userdetails.getCategory().equalsIgnoreCase(loginDto.getCategory()))) {
				System.out.println("i am inside if");
				String error = "you have entered wrong category";
				return buildResponseEntity(new StatusMessages(HttpStatus.BAD_REQUEST, error));
			}
		}
		if (!userRepository.existsByEmail(loginDto.getEmail())) {

			String error = "user not found";
			return buildResponseEntity(new StatusMessages(HttpStatus.BAD_REQUEST, error));
		}
		String message = "login sucessful";

		Authentication authentication = authenticationManager.authenticate(auth);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		
//		  return buildResponseEntity(new StatusMessages(HttpStatus.OK, user, message));
		return buildResponseEntity(new StatusMessages(HttpStatus.OK, jwt));
		 
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody SignupDto signUpRequest) {

		System.out.println("hi there i am in controller");
		if (userRepository.existsByUsername(signUpRequest.getUser_name())) {
			String error = "Error:Username is already taken";
			return buildResponseEntity(new StatusMessages(HttpStatus.BAD_REQUEST, error));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			String error = "Error:Email already exists";
			return buildResponseEntity(new StatusMessages(HttpStatus.BAD_REQUEST, error));
		}

		// Create new user's account

		User user = new User(signUpRequest.getUser_name(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getAddress(), signUpRequest.getLongitude(),
				signUpRequest.getLattitude(), signUpRequest.getCountry(), signUpRequest.getCity(),
				signUpRequest.getState(), signUpRequest.getPhone_number(), signUpRequest.getCategory(),
				signUpRequest.getLiscence());
          System.out.println(user);
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(EnumRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(EnumRoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(EnumRoles.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(EnumRoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);

		userRepository.save(user);

		String message = "Registration Successful";
		Optional<User> us = userRepository.findByEmail(signUpRequest.getEmail());
		return buildResponseEntity(new StatusMessages(HttpStatus.OK, us, message));
	}



	



	private ResponseEntity<Object> buildResponseEntity(StatusMessages message) {
		return new ResponseEntity<>(message, message.getStatus());
	}

}
