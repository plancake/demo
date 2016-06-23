package com.danieleocchipinti.demo.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller("HttpPublic")
@RequestMapping(value="/")
public class Public {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String publicHome(Model model) {
        return "public_home";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }     

}