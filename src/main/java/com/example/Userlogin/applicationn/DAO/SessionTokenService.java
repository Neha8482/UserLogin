package com.example.Userlogin.applicationn.DAO;

import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.example.Userlogin.applicationn.DTO.User;

@Service
public interface SessionTokenService {

	String generateToken(User user);
	boolean validateToken(String token,User user);
	boolean isSessionCreated(User user);
	
}
