package com.danieleocchipinti.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {
	
	@Value("${spring.profiles.active}")
	private String activeProfile;

    public static void main(String[] args) throws Exception {
    	
    	SpringApplication app = new SpringApplication(Application.class);
    	
    	app.setBannerMode(Banner.Mode.OFF);
    	
    	TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    	
        app.run(args);
    }
    
    @PostConstruct
    public void init()
    {
    	if (this.activeProfile.equals("default")) {
    		throw new RuntimeException("A profile needs to be passed on the command line on start - e.g. -Dspring.profiles.active=dev");    		
    	}
    }

}
