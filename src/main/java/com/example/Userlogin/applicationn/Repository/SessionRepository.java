package com.example.Userlogin.applicationn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Userlogin.applicationn.DTO.SessionDTO;

@Repository
public interface SessionRepository extends JpaRepository<SessionDTO,Integer> {

}
