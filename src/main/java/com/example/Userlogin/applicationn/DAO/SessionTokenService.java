package com.example.Userlogin.applicationn.DAO;

import java.net.http.HttpResponse;

import com.example.Userlogin.applicationn.DTO.User;

public interface SessionTokenService {

	String generateToken(User user);
	boolean validateToken(String token,User user);
	
}
