package com.danieleocchipinti.demo.web.controller.http;


import java.awt.PageAttributes.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danieleocchipinti.demo.entity.Deal;
import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.entity.User;
import com.danieleocchipinti.demo.entity.UserRole;
import com.danieleocchipinti.demo.repository.DealRepository;
import com.danieleocchipinti.demo.repository.DocumentRepository;
import com.danieleocchipinti.demo.repository.UserRepository;
import com.danieleocchipinti.demo.lib.CurrentUser;
import com.danieleocchipinti.demo.lib.Utils;

@Controller("HttpBuyerAccount")
@RequestMapping(value="/account/buyer")
public class BuyerAccount {
	
	private static final Logger logger = Logger.getLogger(BuyerAccount.class);	
	
	@Autowired
	private DealRepository dealRepository;	
	
	@Autowired
	private UserRepository userRepository;	
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String buyerHome(Model model) {

		List<Deal> deals = dealRepository.findAllByBuyer((new CurrentUser(userRepository)).getUser()); 		
		
		model.addAttribute("deals", deals); 		
		
		return "account_buyer";
	}
}