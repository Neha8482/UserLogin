package com.example.Userlogin.applicationn.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Userlogin.applicationn.DAO.impl.UserServiceImpl;
import com.example.Userlogin.applicationn.Repository.UserRepository;
import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User()); // Add some users for testing
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users.size(), result.size());
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testAddUser() {
        User user = new User(); // Create a user for testing
        userService.addUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(); // Create a user for testing
        userService.updateUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUser() throws Exception {
        String email = "test@example.com";
        User user = new User(); // Create a user for testing
        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userService.getUser(email);

        assertEquals(user, result);
    }

    @Test
    public void testUpdateLastLogin() {
        Date date = new Date();
        int id = 1;
        userService.updateLastLogin(date, id);

        verify(userRepository, times(1)).updateUserLastLogin(date, id);
    }
}