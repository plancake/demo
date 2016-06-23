package com.danieleocchipinti.demo.web.controller.http;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("HttpAccount")
@RequestMapping(value="/account")
public class Account {
	
	private static final Logger logger = Logger.getLogger(Account.class);	

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String accountHome(Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("name", auth.getName());    	
    	
    	// logger.info(auth.getPrincipal().toString());
    	// logger.info(auth.getDetails().toString());
    	
        /*
    	for (GrantedAuthority ga : auth.getAuthorities()) {
    		logger.info(ga.getAuthority()); // ROLE_USER
    	}
    	*/
    	
        return "account_home";
    }

}