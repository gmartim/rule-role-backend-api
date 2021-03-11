package com.ecore.rulerole.entity.role.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDto {

	private UUID id;

	private String name;

}
