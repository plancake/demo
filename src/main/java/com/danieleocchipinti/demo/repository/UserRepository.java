package com.danieleocchipinti.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.entity.User;
import com.danieleocchipinti.demo.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
    
    User findOneById(int id);
    
    public List<User> findAllByRole(UserRole userRole);
    
    public List<User> findAll();
}

