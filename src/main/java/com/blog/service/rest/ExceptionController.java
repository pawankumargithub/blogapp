package com.blog.service.rest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.service.dto.ErrorResponse;
import com.blog.service.exception.CategoryNotFoundException;
import com.blog.service.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new LinkedHashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMap.put(fieldName, message);
		});
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);

		
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundExcepion(UserNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Date(),HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(errorResponse,errorResponse.getStatusCode());
		
	}
	
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),new Date(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorResponse,errorResponse.getStatusCode());
		
	}
}
