package com.ecore.rulerole.entity.membership.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipCreateDto {

	@NotNull
	private UUID roleId;

	@NotNull
	private UUID userId;

	@NotNull
	private UUID teamId;

}
