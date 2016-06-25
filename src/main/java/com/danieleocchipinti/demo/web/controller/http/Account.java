package com.danieleocchipinti.demo.web.controller.http;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.lib.CurrentUser;
import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.repository.DocumentRepository;
import com.danieleocchipinti.demo.repository.UserRepository;

@Controller("HttpAccount")
@RequestMapping(value="/account")
public class Account {
	
	private static final Logger logger = Logger.getLogger(Account.class);	

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String accountHome(Model model) {
    	
    	CurrentUser currentUser = new CurrentUser(userRepository);

    	model.addAttribute("name", currentUser.getEmail());
    	
    	if (currentUser.isBuyer()) {
    		return "redirect:/account/buyer";
    	}

    	if (currentUser.isSeller()) {
    		return "redirect:/account/seller";
    	}    	
    	
        return "account_home";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/document/{document_id}.pdf", produces = "application/pdf")
	@ResponseBody
	public byte[] getDocument(@PathVariable("document_id") int documentId, HttpServletResponse response) {

	      Document document = documentRepository.findOneById(documentId);
	      
	      return document.getContent();
	}
	
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/hashed/{plain_password}")
	@ResponseBody
	public String getHashedPassword(@PathVariable("plain_password") String plainPassword) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(plainPassword);
		
	}
    */
}