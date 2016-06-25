package com.danieleocchipinti.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
}

