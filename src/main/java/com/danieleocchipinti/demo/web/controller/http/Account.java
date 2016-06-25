package com.danieleocchipinti.demo.web.controller.http;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danieleocchipinti.demo.entity.Document;

import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.repository.DocumentRepository;

@Controller("HttpAccount")
@RequestMapping(value="/account")
public class Account {
	
	private static final Logger logger = Logger.getLogger(Account.class);	

	@Autowired
	private DocumentRepository documentRepository;		
	
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

	@RequestMapping(method = RequestMethod.GET, value = "/document/{document_id}.pdf", produces = "application/pdf")
	@ResponseBody
	public byte[] getDocument(@PathVariable("document_id") int documentId, HttpServletResponse response) {

	      Document document = documentRepository.findOneById(documentId);
	      
	      return document.getContent();
	}
    
    
}