package com.example.Userlogin.applicationn.DAO.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.Userlogin.applicationn.DAO.SessionTokenService;
import com.example.Userlogin.applicationn.DTO.User;

public class SessionServiceImpl implements SessionTokenService{

	Map<Integer,String> map = new HashMap<>();
	
	@Override
	public String generateToken(User user) {
		String token = UUID.randomUUID().toString()+user.getId();
		map.put(user.getId(), token);
		return token;
	}

	@Override
	public boolean validateToken(String token, User user) {
		if(map.containsKey(user.getId())) {
			if(token.equals(map.get(user.getId()))) return true;
		}
		return false;
	}
}
