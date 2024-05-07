package com.example.Userlogin.applicationn.Controllers;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		System.out.print("Here it is");
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> createUser(@RequestBody User user) throws ValidationException {
		System.out.print(user);
		User userIn = userService.findUserByEmail(user.getEmail());
		if(userIn != null) {
			throw new ValidationException("User with email already exists");
		}
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Void> UpdateUser(@RequestBody User user) throws Exception{
		System.out.print(user);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteUser(@RequestParam Integer id) throws Exception{
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
