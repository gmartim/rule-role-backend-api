package com.ecore.rulerole.entity.role.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCreateDto {

	@NotNull
	@Size(min = 1, max = 50)
	private String name;

}
