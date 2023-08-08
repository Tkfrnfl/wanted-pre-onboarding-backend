package com.example.wanted.repository;

import com.example.wanted.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{
    boolean existsByEmailAndPassword
            (String email,String password);
    List<User> findUsersByEmail(String email);
}
