package com.ecore.rulerole.entity.membership;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.ecore.rulerole.entity.role.Role;

public interface MembershipRepository extends Repository<Membership, UUID> {

	Membership save(Membership membership);

	Optional<Membership> findByRoleIdAndUserIdAndTeamId(UUID roleId, UUID userId, UUID teamId);

	@Query("select membership.role from Membership membership where membership.userId = :userId and membership.teamId = :teamId")
	List<Role> findRoleByUserIdAndTeamId(UUID userId, UUID teamId);

	List<Membership> findByRoleId(UUID roleId);

	void deleteById(UUID id);

}
