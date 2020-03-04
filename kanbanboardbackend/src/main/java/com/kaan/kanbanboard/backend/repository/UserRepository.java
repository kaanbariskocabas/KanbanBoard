package com.kaan.kanbanboard.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.User;

/**
 * @author Kaan Kocabas
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
	public User findOneByEmailIgnoreCase(String email);
	
//	public List<User> findByRoles(Set<Role> roles);
	
}
