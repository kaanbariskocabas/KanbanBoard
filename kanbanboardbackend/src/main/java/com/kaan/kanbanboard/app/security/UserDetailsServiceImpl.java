package com.kaan.kanbanboard.app.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaan.kanbanboard.backend.model.User;
import com.kaan.kanbanboard.backend.model.security.CustomUserDetails;
import com.kaan.kanbanboard.backend.repository.UserRepository;


/**
 * @author Kaan Kocabas
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		
		optionalUser
		.orElseThrow(() -> new UsernameNotFoundException("UserName is not found!"));
		
		return optionalUser.map(CustomUserDetails::new).get();
	}

}
