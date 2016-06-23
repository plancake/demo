package com.danieleocchipinti.demo.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller("HttpTest")
@RequestMapping(value="/")
public class Test {

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }   

}