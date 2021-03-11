package com.ecore.rulerole.entity.membership;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecore.rulerole.entity.role.Role;
import com.ecore.rulerole.entity.role.RoleRepository;
import com.ecore.rulerole.entity.team.Team;
import com.ecore.rulerole.entity.team.TeamRepository;
import com.ecore.rulerole.entity.user.User;
import com.ecore.rulerole.entity.user.UserRepository;
import com.ecore.rulerole.exception.RuleRoleException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MembershipService {

	@Autowired
	private MembershipRepository membershipRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeamRepository teamRepository;

	public Membership create(Membership membership) {
		log.info("Started create");

		if (membership.getId() != null) {
			throw new RuleRoleException("membership.id should be null");
		}

		if (membership.getRoleId() == null) {
			throw new RuleRoleException("membership.roleId must not be null");
		}

		if (membership.getUserId() == null) {
			throw new RuleRoleException("membership.userId must not be null");
		}

		if (membership.getTeamId() == null) {
			throw new RuleRoleException("membership.teamId must not be null");
		}

		Optional<Role> optionalRole;

		optionalRole = roleRepository.findById(membership.getRoleId());

		if (optionalRole.isEmpty()) {
			throw new RuleRoleException("membership.roleId was not found");
		}

		User user;

		user = userRepository.findById(membership.getUserId());

		if (user == null) {
			throw new RuleRoleException("membership.userId was not found");
		}

		boolean userHasTeam;

		userHasTeam = false;

		for (UUID teamId : user.getTeamId()) {
			if (teamId.equals(membership.getTeamId())) {
				userHasTeam = true;

				break;
			}
		}

		if (!userHasTeam) {
			throw new RuleRoleException("user.teamId does not contain membership.teamId");
		}

		Team team;

		team = teamRepository.findById(membership.getTeamId());

		if (team == null) {
			throw new RuleRoleException("membership.teamId was not found");
		}

		Optional<Membership> optionalMembership;

		optionalMembership = membershipRepository.findByRoleIdAndUserIdAndTeamId(membership.getRoleId(),
				membership.getUserId(), membership.getTeamId());

		if (optionalMembership.isPresent()) {
			throw new RuleRoleException("membership already exists");
		}

		return membershipRepository.save(membership);
	}

	public List<Role> readRolesByUserIdAndTeamId(UUID userId, UUID teamId) {
		log.info("Started readRolesByUserIdAndTeamId");

		if (userId == null) {
			throw new RuleRoleException("userId must not be null");
		}

		if (teamId == null) {
			throw new RuleRoleException("teamId must not be null");
		}

		return membershipRepository.findRoleByUserIdAndTeamId(userId, teamId);
	}

	public List<Membership> readByRoleId(UUID roleId) {
		log.info("Started readByRoleId");

		if (roleId == null) {
			throw new RuleRoleException("roleId must not be null");
		}

		return membershipRepository.findByRoleId(roleId);
	}

	public void deleteByRoleIdAndUserIdAndTeamId(UUID roleId, UUID userId, UUID memberId) {
		Membership membership;

		membership = membershipRepository.findByRoleIdAndUserIdAndTeamId(roleId, userId, memberId)
				.orElseThrow(() -> new RuleRoleException("membership was not found"));

		membershipRepository.deleteById(membership.getId());
	}

}
