package com.danieleocchipinti.demo.lib;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.danieleocchipinti.demo.entity.User;
import com.danieleocchipinti.demo.entity.UserRole;
import com.danieleocchipinti.demo.repository.UserRepository;

public class CurrentUser {
	
	private static final Logger logger = Logger.getLogger(CurrentUser.class);
	
	User user;
	
	public CurrentUser(UserRepository userRepository) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		user = userRepository.findOneByEmail(auth.getName());	
	}

	public int getId()
	{
		return user.getId();
	}

	public String getEmail()
	{
		return user.getEmail();
	}
	
	public boolean isSeller()
	{
		return this.getRole().equals(UserRole.ROLE_SELLER);
	}

	public boolean isBuyer()
	{
		return this.getRole().equals(UserRole.ROLE_BUYER);
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	private UserRole getRole()
	{
		return user.getRole();
	}	
}