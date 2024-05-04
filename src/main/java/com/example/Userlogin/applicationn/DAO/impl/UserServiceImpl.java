package com.example.Userlogin.applicationn.DAO.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Userlogin.applicationn.DAO.UserService;
import com.example.Userlogin.applicationn.DTO.User;
import com.example.Userlogin.applicationn.Repository.UserRepository;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
         userRepository.deleteById(id);
         return;
    }

	@Override
	public void addUser(User user) {
		 userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		 userRepository.save(user);
	}

	@Override
	public User getUser(String email) throws Exception {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
		return user.isPresent() ?  user.get():null;
	}

	@Override
	@Transactional
	public void updateLastLogin(Date date,int id) {
		userRepository.updateUserLastLogin(date, id);
	}
}