package com.ecore.rulerole.entity.user;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class UserRepository {

	public List<User> findAll() {
		WebClient webClient;

		webClient = WebClient.create("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users");

		Mono<Object[]> response;

		response = webClient.get().retrieve().bodyToMono(Object[].class).log();

		Object[] objects;

		objects = response.block();

		ObjectMapper mapper;

		mapper = new ObjectMapper();

		return Arrays.stream(objects).map(object -> mapper.convertValue(object, User.class))
				.collect(Collectors.toList());
	}

	public User findById(UUID id) {
		WebClient webClient;

		webClient = WebClient.create("https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/" + id);

		Mono<User> mono;

		mono = webClient.get().retrieve().bodyToMono(User.class);

		User user;

		user = mono.block();

		return user;
	}

}
