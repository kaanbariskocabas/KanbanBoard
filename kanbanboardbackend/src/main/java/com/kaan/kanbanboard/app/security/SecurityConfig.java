package com.kaan.kanbanboard.app.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kaan.kanbanboard.backend.model.type.RoleType;

/**
 * @author Kaan Kocabas
 *
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private CustomLogoutSuccessHandler logoutHandler;
	
	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	private SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

//	@Bean
//	public CustomLogoutSuccessHandler logoutHandler() {
//		return new CustomLogoutSuccessHandler();
//	}
//	
//	@Bean
//	public RestAuthenticationEntryPoint authenticationEntryPoint() {
//		return new RestAuthenticationEntryPoint();
//	}
//	
//	@Bean
//	public AuthenticationSuccessHandler successHandler() {
//		return new AuthenticationSuccessHandler();
//	}
//
//	@Bean
//	public SimpleUrlAuthenticationFailureHandler failureHandler() {
//		return new SimpleUrlAuthenticationFailureHandler();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
	    .exceptionHandling()
	    .authenticationEntryPoint(authenticationEntryPoint)
		.and()
			.formLogin()
			.loginProcessingUrl("/api/login")
			.successHandler(successHandler)
			.failureHandler(failureHandler)
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/api/logout")
			.logoutSuccessHandler(logoutHandler)
			.permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/api/admin/**")
			.hasRole(RoleType.ADMIN.name())
			.antMatchers("/api/**")
			.authenticated();
		// Needed for the h2 console to work!!!
		http.headers().frameOptions().disable();
	}

}