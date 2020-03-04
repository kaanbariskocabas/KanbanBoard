package com.kaan.kanbanboard.app.datagenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kaan.kanbanboard.backend.model.Role;
import com.kaan.kanbanboard.backend.model.Task;
import com.kaan.kanbanboard.backend.model.User;
import com.kaan.kanbanboard.backend.model.type.RoleType;
import com.kaan.kanbanboard.backend.repository.RoleRepository;
import com.kaan.kanbanboard.backend.repository.TaskRepository;
import com.kaan.kanbanboard.backend.repository.UserRepository;

/**
 * @author Kaan Kocabas
 *
 */
@Component
public class DataGenerator {

	private List<User> users = new ArrayList<>();
	private List<Task> tasks = new ArrayList<>();
	private Set<Role> roles = new HashSet<>();

	@Bean
	public CommandLineRunner loadData(TaskRepository taskRepository, UserRepository userRepository,
			RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

		return args -> {
			if (hasData(userRepository)) {
				System.out.println("Using existing database");
				return;
			}
			System.out.println("... generating roles");
			createRoles(roleRepository);
			System.out.println("... generating users");
			createUsers(userRepository, bCryptPasswordEncoder, roleRepository);
			System.out.println("... generating todos");
			createTasks(taskRepository);
		};
	}

	private void createRoles(RoleRepository roleRepository) {
		roles.add(roleRepository.save(new Role(RoleType.ADMIN)));
		roles.add(roleRepository.save(new Role(RoleType.USER)));
	}

	private void createUsers(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		tasks.add(new Task("Generated Title", "Generated Content"));
		tasks.add(new Task("Generated Title2", "Generated Content2"));
		users.add(userRepository
				.save(new User("kaan", "kocabas", bCryptPasswordEncoder.encode("kaan"), "kk@gmail.com", roles, tasks)));
		List<Task> taskList = new ArrayList<>();
		taskList.add(new Task("User Title", "User Content"));
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(new Role(RoleType.USER));
		User user2 = new User();
		user2.setEmail("user@user.com");
		Role role = roleRepository.findByRole(RoleType.USER).get();
		user2.addRole(role);
		user2.setPassword(bCryptPasswordEncoder.encode("user"));
		user2.setTasks(taskList);
		users.add(userRepository.save(user2));
	}

	private void createTasks(TaskRepository taskRepository) {
//		users.forEach(u -> {
//			todos.add(todoRepository.save(new Todo("Generated Title", "Generated Content")));
//		});
	}

	private boolean hasData(UserRepository userRepository) {
		return userRepository.count() != 0L;
	}

}
