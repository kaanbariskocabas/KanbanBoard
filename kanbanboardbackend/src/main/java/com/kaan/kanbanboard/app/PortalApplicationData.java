package com.kaan.kanbanboard.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Kaan Kocabas
 *
 */
@Configuration
//https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
//@EnableJpaRepositories(basePackages = "ch.novadi.portal.backend.data")
//@EntityScan("ch.novadi.portal.backend.data")
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//@ComponentScan("ch.kaan.*")
//@EnableScheduling
public class PortalApplicationData {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
