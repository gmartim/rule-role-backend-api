package com.ecore.rulerole;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecore.rulerole.entity.membership.Membership;
import com.ecore.rulerole.entity.membership.MembershipRepository;
import com.ecore.rulerole.entity.role.Role;
import com.ecore.rulerole.entity.role.RoleRepository;
import com.ecore.rulerole.entity.user.User;
import com.ecore.rulerole.entity.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MembershipRepository membershipRepository;

	public static void main(String... arguments) {
		log.info("Started main");

		SpringApplication.run(Application.class, arguments);
	}

	@Override
	public void run(String... arguments) throws Exception {
		log.info("Started run");

		Role roleDeveloper, roleProductOwner, roleTester;

		roleDeveloper = Role.builder().name("Developer").build();

		roleRepository.save(roleDeveloper);

		log.debug("[{}]", roleDeveloper);

		roleProductOwner = Role.builder().name("Product Owner").build();

		roleRepository.save(roleProductOwner);

		log.debug("[{}]", roleProductOwner);

		roleTester = Role.builder().name("Tester").build();

		roleRepository.save(roleTester);

		log.debug("[{}]", roleTester);

		List<User> users;

		users = userRepository.findAll();

		Membership membership;

		for (User user : users) {
			for (UUID teamId : user.getTeamId()) {
				membership = Membership.builder().roleId(roleDeveloper.getId()).teamId(teamId).userId(user.getId())
						.build();

				membership = membershipRepository.save(membership);

				log.debug("[{}]", membership);
			}
		}
	}

}
