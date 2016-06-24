package com.danieleocchipinti.demo.web.controller.http;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("HttpSellerAccount")
@RequestMapping(value="/account/seller")
public class SellerAccount {
	
	private static final Logger logger = Logger.getLogger(SellerAccount.class);	

	private static final String UPLOADED_FILES_ROOT_DIR = "/var/tmp";
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String provideUploadInfo(Model model) {
		
		File rootFolder = new File(UPLOADED_FILES_ROOT_DIR);

		model.addAttribute("files",
			Arrays.stream(rootFolder.listFiles())
					.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
					.map(f -> f.getName())
					.collect(Collectors.toList())
		);

		return "account_seller";
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public String handleFileUpload(@RequestParam("name") String name,
								   @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
		
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("errorMessage", "Folder separators not allowed");
			return "redirect:/account/seller";
		}

		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(UPLOADED_FILES_ROOT_DIR + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("successMessage",
						"You successfully uploaded " + name + "!");
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage",
						"You failed to upload " + name + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage",
					"You failed to upload " + name + " because the file was empty");
		}

		return "redirect:/account/seller";
	}


}