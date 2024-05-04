package com.example.Userlogin.applicationn.Controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import javax.xml.bind.ValidationException;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;
import com.example.Userlogin.applicationn.DTO.UserLoginRequest;

public class LoginControllerTest {

    @Mock
    private UserService userService;
    
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginController loginController;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testLoginUser_ValidCredentials() throws Exception {
        UserLoginRequest request = new UserLoginRequest("test@example.com", "password");
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        String encodedPassword = new BCryptPasswordEncoder().encode("password");
        user.setPassword("password");
        
        System.out.println("Encoded Password: " + encodedPassword);

        when(userService.getUser("test@example.com")).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        ResponseEntity<String> response = loginController.loginUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successfull", response.getBody());
        verify(userService).updateLastLogin(any(Date.class), eq(1));
    }

    @Test
    public void testLoginUser_InvalidEmail() throws Exception {
        UserLoginRequest request = new UserLoginRequest(null, "password");

        ResponseEntity<String> responseEntity = loginController.loginUser(request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testLoginUser_InvalidPassword() throws Exception {
        UserLoginRequest request = new UserLoginRequest("test@example.com", "password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(new BCryptPasswordEncoder().encode("wrongpassword"));

        when(userService.getUser("test@example.com")).thenReturn(user);

        assertThrows(ValidationException.class, () -> loginController.loginUser(request));
    }

    @Test
    public void testLoginUser_UserNotFound() throws Exception {
        UserLoginRequest request = new UserLoginRequest("test@example.com", "password");

        when(userService.getUser("test@example.com")).thenReturn(null);

        assertThrows(ValidationException.class, () -> loginController.loginUser(request));
    }
}