package com.example.Userlogin.applicationn.DAO;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Userlogin.applicationn.DTO.User;

@Service
public interface UserService{
	
	List<User> getAllUsers();
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(Integer id);
	
	User getUser(String email) throws Exception;
	
	void updateLastLogin(Date localDate,int id);
	
	User getUserById(Integer id);
	
	User findUserByEmail(String email);
	
}

