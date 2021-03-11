package com.ecore.rulerole.entity.membership;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecore.rulerole.entity.membership.dto.MembershipCreateDto;
import com.ecore.rulerole.entity.membership.dto.MembershipDto;
import com.ecore.rulerole.entity.role.Role;
import com.ecore.rulerole.entity.role.dto.RoleDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/memberships")
@Slf4j
public class MembershipController {

	@Autowired
	private MembershipService membershipService;

	@PostMapping
	public MembershipDto post(@Valid @RequestBody(required = true) MembershipCreateDto membershipCreateDto) {
		log.info("Started post");

		Membership membership;

		membership = Membership.builder().roleId(membershipCreateDto.getRoleId())
				.teamId(membershipCreateDto.getTeamId()).userId(membershipCreateDto.getUserId()).build();

		membership = membershipService.create(membership);

		return MembershipDto.builder().id(membership.getId()).roleId(membership.getRoleId())
				.teamId(membership.getTeamId()).userId(membership.getUserId()).build();
	}

	@GetMapping("/{userId}/{teamId}")
	public List<RoleDto> get(@PathVariable(required = true, name = "userId") UUID userId,
			@PathVariable(required = true, name = "teamId") UUID teamId) {
		log.info("Started handling GET to /memberships/{userId}/{teamId}");

		List<Role> roles;

		roles = membershipService.readRolesByUserIdAndTeamId(userId, teamId);

		List<RoleDto> roleDtos;

		roleDtos = roles.stream().map(role -> RoleDto.builder().id(role.getId()).name(role.getName()).build())
				.collect(Collectors.toList());

		return roleDtos;
	}

	@GetMapping("/{roleId}")
	public List<MembershipDto> get(@PathVariable(required = true, name = "roleId") UUID roleId) {
		log.info("Started handling GET to /memberships/{roleId}");

		List<Membership> memberships;

		memberships = membershipService.readByRoleId(roleId);

		List<MembershipDto> membershipDtos;

		membershipDtos = memberships.stream()
				.map(membership -> MembershipDto.builder().id(membership.getId()).roleId(membership.getRoleId())
						.teamId(membership.getTeamId()).userId(membership.getUserId()).build())
				.collect(Collectors.toList());

		return membershipDtos;
	}

	@DeleteMapping("/{roleId}/{userId}/{teamId}")
	public ResponseEntity<Void> delete(@PathVariable(required = true, name = "roleId") UUID roleId,
			@PathVariable(required = true, name = "userId") UUID userId,
			@PathVariable(required = true, name = "teamId") UUID teamId) {
		log.info("Started handling DELETE to /memberships/{roleId}/{userId}/{teamId}");

		membershipService.deleteByRoleIdAndUserIdAndTeamId(roleId, userId, teamId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
