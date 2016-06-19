package com.danieleocchipinti.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

    @RequestMapping("/")
    String home() {
        return "Hello World 6!";
    }

    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(Application.class);
    	
    	app.setBannerMode(Banner.Mode.OFF);
    	
        app.run(args);
    }

}
