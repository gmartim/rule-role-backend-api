package com.ecore.rulerole.entity.role;

import org.junit.jupiter.api.Assertions;

//@SpringBootTest
//@DataJpaTest
//@Import(RoleRepository.class)
public class RoleRepositoryTest {

//	@Autowired
	private RoleRepository roleRepository;

//	@Test
	public void testSave() {
		Role role;

		role = Role.builder().name("Developer").build();

		role = roleRepository.save(role);

		Assertions.assertNotNull(role.getId());
	}

}
