package com.goeuro.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.goeuro.testapp.controller.APIController;

@SpringBootApplication
public class GoEuroTestApp implements CommandLineRunner {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	APIController aPIController;
	
    public static void main(String args[]) {
        SpringApplication.run(GoEuroTestApp.class,args);
    }

	public void run(String... args) throws Exception {
		aPIController.consumeAPI(args[0]);
	}

}
