package com.example.Userlogin.applicationn.Validators;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.Userlogin.applicationn.DAO.SessionTokenService;
import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor{
	
	@Autowired
	SessionTokenService sessionTokenService;
	
	@Autowired
	UserService userService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractSessionToken(request);
        System.out.print("HERE in tokenService "+token);
        if (token != null) {
            // Extract user ID from token
            String userId = getUserIdFromToken(token);
            if (userId != null) {
                // Retrieve user from database
                User user = userService.getUserById(Integer.parseInt(userId));
                if (user != null && sessionTokenService.validateToken(token, user)) {
                    // Check if session token is valid
                        // Update last login time
                        userService.updateLastLogin(new Date(), user.getId());
                        return true;
                }
            }
            response.sendRedirect("/login");
            return false;
        }else {
        	return true;
        }
        // If token is missing, invalid, or expired, redirect to login page
       
    }

    private String extractSessionToken(HttpServletRequest request) {
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        System.out.print("hErerrere");
        return null;
    }

    private String getUserIdFromToken(String token) {
        try {
            String id = token.substring(16);
            return id;
        } catch (Exception e) {
            return null; // Invalid token format or signature
        }
    }

}
