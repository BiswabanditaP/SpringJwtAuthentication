package com.biswa.JWTAuthentication.security;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class SpringAuditing implements AuditorAware<String> {
Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
	@Override
	public Optional<String> getCurrentAuditor() {
	      if(authentication==null||!authentication.isAuthenticated()||authentication instanceof AnonymousAuthenticationToken) {
	    	  return Optional.empty(); 
	      }
		
		return Optional.ofNullable(authentication.getName());
	}

}
