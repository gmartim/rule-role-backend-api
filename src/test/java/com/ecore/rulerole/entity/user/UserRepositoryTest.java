package com.ecore.rulerole.entity.user;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
//@Import(UserRepository.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

//	@Test
	public void testFindAll() {
		List<User> users;

		users = userRepository.findAll();

		Assertions.assertNotNull(users);
		Assertions.assertFalse(users.isEmpty());
		Assertions.assertTrue(users.size() > 0);

		User user;

		user = users.get(0);

		Assertions.assertNotNull(user);
	}

//	@Test
	public void testFindById() {
		User user;

		user = userRepository.findById(UUID.fromString("4408768c-6519-4325-92fb-7404db94e8a0"));

		Assertions.assertNotNull(user);
		Assertions.assertEquals("Angela", user.getName());

		user = userRepository.findById(UUID.fromString("aaaaaaaa-6519-4325-92fb-7404db94e8a0"));

		Assertions.assertNull(user);
	}

}
