package com.example.Userlogin.applicationn.DTO;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@JsonIgnoreProperties(ignoreUnknown = true)
@Table
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column
	private String name;
	@Column
    private String email;
	@Column
    private String password;
	@Column
    private Date lastLogin;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public boolean checkPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(this.password, password);
	}
	
	public void setPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.password = bCryptPasswordEncoder.encode(password);
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", lastLogin="
				+ lastLogin + "]";
	}
	
}
