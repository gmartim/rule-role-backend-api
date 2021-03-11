package com.ecore.rulerole.entity.role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecore.rulerole.exception.RuleRoleException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role create(Role role) {
		log.info("Started create");

		if (role.getId() != null) {
			throw new RuleRoleException("role.id should be null");
		}

		Optional<Role> optional;

		optional = roleRepository.findByName(role.getName());

		if (optional.isPresent()) {
			throw new RuleRoleException("role.name already exists");
		}

		return roleRepository.save(role);
	}

	public List<Role> readAll() {
		log.debug("Started readAll");

		return roleRepository.findAll();
	}

	public Role readById(UUID id) {
		log.debug("Started readById");

		Role role;

		role = roleRepository.findById(id).orElseThrow(() -> new RuleRoleException("role.id not present"));

		return role;
	}

}
