package com.ecore.rulerole.entity.role;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

//@SpringBootTest
@Import(RoleService.class)
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;

	@MockBean
	private RoleRepository roleRepository;

//	@Test
	public void createWhenValidRole() {
		Role newRole, createdRole;

		newRole = Role.builder().name("Tester").build();
		createdRole = Role.builder().id(UUID.randomUUID()).name("Tester").build();

		BDDMockito.given(roleRepository.save(BDDMockito.any())).willReturn(createdRole);

		newRole = roleService.create(newRole);

		Assertions.assertNotNull(newRole);
		Assertions.assertNotNull(newRole.getId());
		Assertions.assertEquals(newRole.getId(), createdRole.getId());
	}

}
