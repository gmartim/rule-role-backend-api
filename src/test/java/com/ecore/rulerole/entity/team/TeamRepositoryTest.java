package com.ecore.rulerole.entity.team;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;

//@SpringBootTest
//@Import(TeamRepository.class)
public class TeamRepositoryTest {

//	@Autowired
	private TeamRepository teamRepository;

//	@Test
	public void testFindAll() {
		List<Team> teams;

		teams = teamRepository.findAll();

		Assertions.assertNotNull(teams);
		Assertions.assertFalse(teams.isEmpty());
		Assertions.assertTrue(teams.size() > 0);
	}

//	@Test
	public void testFindById() {
		Team team;

		team = teamRepository.findById(UUID.fromString("bdad9afe-63c3-4ff2-ae6b-f1143d92ca15"));

		Assertions.assertNotNull(team);
		Assertions.assertEquals("Lyrical Trout", team.getName());
	}

}
