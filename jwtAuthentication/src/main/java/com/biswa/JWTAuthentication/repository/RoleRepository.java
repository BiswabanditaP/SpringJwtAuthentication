package com.biswa.JWTAuthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biswa.JWTAuthentication.model.EnumRoles;
import com.biswa.JWTAuthentication.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	Optional<Role> findByName(EnumRoles name);
}
