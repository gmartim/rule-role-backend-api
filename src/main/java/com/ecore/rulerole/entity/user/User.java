package com.ecore.rulerole.entity.user;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private UUID id;

	private String name;

	private List<UUID> teamId;

}
