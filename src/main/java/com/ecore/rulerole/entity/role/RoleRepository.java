package com.ecore.rulerole.entity.role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, UUID> {

	Role save(Role role);

	Optional<Role> findByName(String name);

	List<Role> findAll();

	Optional<Role> findById(UUID id);

}
