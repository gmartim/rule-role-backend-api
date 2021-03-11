package com.ecore.rulerole.exception.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecore.rulerole.exception.RuleRoleException;
import com.ecore.rulerole.exception.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuleRoleException.class)
	public ResponseEntity<ErrorDto> handleRuleRoleException(RuleRoleException exception) {
		log.error("Error occurred", exception);

		ErrorDto errorDto;

		errorDto = ErrorDto.builder().message(exception.getMessage()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
		log.error("Error occurred", exception);

		ErrorDto errorDto;

		errorDto = ErrorDto.builder().message(exception.getMessage()).build();

		return handleExceptionInternal(exception, errorDto, httpHeaders, httpStatus, webRequest);
	}

}
