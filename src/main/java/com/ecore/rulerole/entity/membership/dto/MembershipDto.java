package com.ecore.rulerole.entity.membership.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MembershipDto {

	private UUID id;

	private UUID roleId;

	private UUID userId;

	private UUID teamId;

}
