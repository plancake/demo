package com.danieleocchipinti.demo.web.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class Test {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getUsers() {
        return "All users";
    }	
	
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public Object getUser(@PathVariable Long userId) {
        return new Object(){ public String firstName = "Firstname"; };
    }
}