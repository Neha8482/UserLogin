package com.example.Userlogin.applicationn;

import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

@ControllerAdvice
//annotation allows us to consolidate our multiple, scattered @ExceptionHandlers from before into a single, global error handling component.
public class ExceptionHandler implements HandlerInterceptor {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class})
	public ResponseEntity<Object> sendResponse(ValidationException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> sendResponse(Exception e){
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, please try again later");
	}
	//more Exception Handlers can be added in this way here
}
