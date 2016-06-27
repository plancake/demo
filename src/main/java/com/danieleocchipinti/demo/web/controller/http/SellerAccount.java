package com.danieleocchipinti.demo.web.controller.http;


import java.awt.PageAttributes.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Controller("HttpSellerAccount")
@RequestMapping(value="/account/seller")
public class SellerAccount {
	
	private static final Logger logger = Logger.getLogger(SellerAccount.class);
	
	private String lastUploadErrorMessage = "";
	
	@Autowired
	private DealRepository dealRepository;	

	@Autowired
	private DocumentRepository documentRepository;	
	
	@Autowired
	private UserRepository userRepository;		
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String viewDeals(Model model) {

		List<Deal> deals = dealRepository.findAllBySeller((new CurrentUser(userRepository)).getUser());
		
        List<User> buyers = userRepository.findAllByRole(UserRole.ROLE_BUYER);
        
		model.addAttribute("buyers", buyers);  		
		
		model.addAttribute("deals", deals); 
		
		model.addAttribute("documents", documentRepository.findAllByOrderByUploadedAtDesc());

		return "account_seller";
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public String createDeal(
			@RequestParam("buyer_id") int buyerId,			
			@RequestParam("description") String description, 
			@RequestParam("file") MultipartFile file,
			@RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3,			
			RedirectAttributes redirectAttributes
	) {
		
		List<MultipartFile> files = new ArrayList<>();
		if (!file.isEmpty()) files.add(file);
		if (!file2.isEmpty()) files.add(file2);
		if (!file3.isEmpty()) files.add(file3);		
		
		for (MultipartFile f : files) {
			if (!isFileValid(f)) {
				redirectAttributes.addFlashAttribute("errorMessage", lastUploadErrorMessage);
				return "redirect:/account/seller";				
			}
		}
		
		// everything is OK!
		
		User seller = (new CurrentUser(userRepository)).getUser();
		
		User buyer = userRepository.findOneById(buyerId);
		
		try {
			
			List<Document> documents = new ArrayList<>();

			Deal deal = new Deal(description, seller, buyer, documents);				
			
			for (MultipartFile f : files) {
				documents.add(new Document(f.getOriginalFilename(), f.getBytes(), deal));
			}

			dealRepository.save(deal);
			
			redirectAttributes.addFlashAttribute("successMessage",
					"You successfully created a new deal!");				
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/account/seller";
	}
	
	private boolean isFileValid (MultipartFile file)
	{
		String errorMessage = "";
		
		String filename = "";
			
		filename = file.getOriginalFilename();

		if (filename.contains("/")) {
			errorMessage = "Folder separators not allowed";
		}			
		
		try {
			
			if (!Utils.is_pdf(file.getBytes())) {
				errorMessage = "Only PDF files are accepted.";		
			}
		}
		catch (Exception e) {
			errorMessage = "You failed to upload " + filename + " => " + e.getMessage();
		}

		lastUploadErrorMessage = errorMessage;
		
		return (errorMessage.length() == 0);
		
	}

}