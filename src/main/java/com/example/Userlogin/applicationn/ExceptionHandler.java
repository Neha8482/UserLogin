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
	public ResponseEntity<Object> sendResponse(){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Somethig wrong with the request, please check and try again");
	}
	
	//more Exception Handlers can be added in this way here

}
