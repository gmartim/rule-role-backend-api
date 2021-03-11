package com.ecore.rulerole.entity.team;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class TeamRepository {

	public List<Team> findAll() {
		WebClient webClient;

		webClient = WebClient.create("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams");
//
//		Mono<List> mono;
//
//		mono = webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<List>() {
//		});
//
//		List list;
//
//		list = mono.block();
//
//		return list;

		Mono<Object[]> response;

		response = webClient.get().retrieve().bodyToMono(Object[].class).log();

		Object[] objects;

		objects = response.block();

		ObjectMapper mapper;

		mapper = new ObjectMapper();

		return Arrays.stream(objects).map(object -> mapper.convertValue(object, Team.class))
				.collect(Collectors.toList());
	}

	public Team findById(UUID id) {
		WebClient webClient;

		webClient = WebClient.create("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/" + id);

		Mono<Team> mono;

		mono = webClient.get().retrieve().bodyToMono(Team.class);

		Team team;

		team = mono.block();

		return team;
	}

}
