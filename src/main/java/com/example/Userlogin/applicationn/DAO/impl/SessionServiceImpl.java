package com.example.Userlogin.applicationn.DAO.impl;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Userlogin.applicationn.DAO.SessionTokenService;
import com.example.Userlogin.applicationn.DTO.SessionDTO;
import com.example.Userlogin.applicationn.DTO.User;
import com.example.Userlogin.applicationn.Repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionTokenService{

	public Map<Integer,String> map = new HashMap<>();
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Override
	public String generateToken(User user) {
		String token = UUID.randomUUID().toString()+user.getId();
		map.put(user.getId(), token);
		sessionRepository.save(new SessionDTO(user.getId(),token));
		return token;
	}

	@Override
	public boolean validateToken(String token, User user) {
		System.out.print(map.size());
		java.util.Optional<SessionDTO> session = sessionRepository.findById(user.getId());
		if(session.isPresent() && token.equals(session.get().getSession())) {
			if(token.equals(map.get(user.getId()))) {
				Date lastLogin = user.getLastLogin();
		        if (lastLogin == null) {
		            return false; // User never logged in before
		        }
		        Date currentTime = new Date();
		        Instant i1 = currentTime.toInstant();
		        Instant i2 = lastLogin.toInstant();
		        return Duration.between(i1, i2).toHours() <= 1;

			}
		}
		return false;
	}

	@Override
	public boolean isSessionCreated(User user) {
		return map.containsKey(user.getId());
	}
}
