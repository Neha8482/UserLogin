package com.example.Userlogin.applicationn.Controllers;

import java.util.Date;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Userlogin.applicationn.DAO.SessionTokenService;
import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;
import com.example.Userlogin.applicationn.DTO.UserLoginRequest;

@RestController
@RequestMapping
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionTokenService sessionTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) throws Exception{
		if(userLoginRequest.getEmail() == null || userLoginRequest.getPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String password = "";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Optional<User> user = null;
		try {
			user = Optional.ofNullable(userService.getUser(userLoginRequest.getEmail()));
			password = user.isPresent() ? user.get().getPassword():"";
		    System.out.print("here "+password);
		}catch(Exception e) {
			System.out.print(e);
			throw new ValidationException("Email recieved is Incorrect,please try again");
		}
		if(!bCryptPasswordEncoder.matches(userLoginRequest.getPassword(),password)) {
			throw new ValidationException("Password recieved is Incorrect,please try again");
		}
		if(user.isPresent()) userService.updateLastLogin(new Date(),user.get().getId());
		sessionTokenService.generateToken(user.get());
		return ResponseEntity.ok("Login Successfull");
	}
}
