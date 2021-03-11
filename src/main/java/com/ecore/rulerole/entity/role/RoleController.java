package com.ecore.rulerole.entity.role;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecore.rulerole.entity.role.dto.RoleCreateDto;
import com.ecore.rulerole.entity.role.dto.RoleDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public RoleDto post(@Valid @RequestBody(required = true) RoleCreateDto roleCreateDto) {
		log.info("Started post");

		Role role;

		role = Role.builder().name(roleCreateDto.getName()).build();

		role = roleService.create(role);

		return RoleDto.builder().id(role.getId()).name(role.getName()).build();
	}

	@GetMapping
	public List<RoleDto> get() {
		log.info("Started handling GET to /roles");

		List<Role> roles;

		roles = roleService.readAll();

		List<RoleDto> roleDtos;

		roleDtos = roles.stream().map(role -> RoleDto.builder().id(role.getId()).name(role.getName()).build())
				.collect(Collectors.toList());

		return roleDtos;
	}

	@GetMapping("/{id}")
	public RoleDto get(@PathVariable(required = true, name = "id") UUID id) {
		log.info("Started handling GET to /roles/{id}");

		Role role;

		role = roleService.readById(id);

		RoleDto roleDto;

		roleDto = RoleDto.builder().id(role.getId()).name(role.getName()).build();

		return roleDto;
	}

}
