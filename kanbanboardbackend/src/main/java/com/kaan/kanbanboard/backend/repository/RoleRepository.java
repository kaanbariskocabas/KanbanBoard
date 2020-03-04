package com.kaan.kanbanboard.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.type.RoleType;

/**
 * @author Kaan Kocabas
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRole(RoleType roleType);
	
}
