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

import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.repository.DocumentRepository;

import com.danieleocchipinti.demo.lib.Utils;

@Controller("HttpSellerAccount")
@RequestMapping(value="/account/seller")
public class SellerAccount {
	
	private static final Logger logger = Logger.getLogger(SellerAccount.class);	

	private static final String UPLOADED_FILES_ROOT_DIR = "/var/tmp";
	
	@Autowired
	private DocumentRepository documentRepository;	
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String provideUploadInfo(Model model) {

		model.addAttribute("documents", documentRepository.findAllByOrderByUploadedAtDesc());

		return "account_seller";
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		String filename = "";
		
		boolean successfulUpload = false;

		if (!file.isEmpty()) {
			
			filename = file.getOriginalFilename();

			if (filename.contains("/")) {
				redirectAttributes.addFlashAttribute("errorMessage", "Folder separators not allowed");
				return "redirect:/account/seller";
			}			
			
			try {
				
				if (!Utils.is_pdf(file.getBytes())) {
					redirectAttributes.addFlashAttribute("errorMessage", "Only PDF files are accepted.");
					return "redirect:/account/seller";					
				}
				
				successfulUpload = true;
				redirectAttributes.addFlashAttribute("successMessage",
						"You successfully uploaded " + filename + "!");
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage",
						"You failed to upload " + filename + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage",
					"You failed to upload " + filename + " because the file was empty");
		}
		
		if (successfulUpload) {
			try {
				documentRepository.save(new Document(filename, file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:/account/seller";
	}

}