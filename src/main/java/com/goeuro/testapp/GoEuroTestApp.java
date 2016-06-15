package com.goeuro.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.goeuro.testapp.controller.APIController;

@SpringBootApplication
public class GoEuroTestApp implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(GoEuroTestApp.class);

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
	    if(args.length != 1){
	        // error message for user
	        System.out.println("Please enter city name");
	        
	        //log error message
	        logger.error("Please enter city name");
	        return;
	    }
		aPIController.consumeAPI(args[0]);
	}

}
