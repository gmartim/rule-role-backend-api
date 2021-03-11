package com.ecore.rulerole.entity.membership;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ecore.rulerole.entity.role.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "membership", uniqueConstraints = {
		@UniqueConstraint(name = "membership_role_id_user_id_team_id", columnNames = { "role_id", "user_id",
				"team_id" }) })
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

	@Id
	@GeneratedValue
	@Column
	private UUID id;

	@Column(name = "role_id", nullable = false)
	private UUID roleId;

	@Column(name = "user_id", nullable = false)
	private UUID userId;

	@Column(name = "team_id", nullable = false)
	private UUID teamId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;

}
