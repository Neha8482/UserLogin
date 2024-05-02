package com.example.Userlogin.applicationn.Repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Userlogin.applicationn.DTO.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom methods, if any, can be defined here
	
	User findByEmail(String email);
	
	@Modifying
	@Query("update User set lastLogin=:date where id=:id")
	void updateUserLastLogin(@Param("date") Date date,@Param("id") int id);
}

