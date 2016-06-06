package com.goeuro.testapp.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import com.goeuro.testapp.model.CityInfo;
import com.goeuro.testapp.service.ConsumeAPIService;
import com.goeuro.testapp.service.FileWriterService;

@Controller
@PropertySource("application.properties")
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

	@Autowired
	ConsumeAPIService consumeAPIService;

	@Autowired
	FileWriterService fileWriterService;
	
    @Value("${goeuro.cityinfo.inputcity.error}")
    private String invalidCityName;

    @Value("${goeuro.cityinfo.api.error}")
    private String invalidServiceResponse;

    @Value("${goeuro.cityinfo.saveresponse.error}")
    private String invalidFileWriteResponse;

	public void consumeAPI(String city) throws Exception{
		if(!isValidCity(city)){
		    
		    logger.error(invalidCityName);
		        return;
		}else{
		    
		    List<CityInfo> cityInfo = null;
		    try{
		        cityInfo = consumeAPIService.getCityInfo(city);
		    }catch(Exception ex){
		        
	            logger.error(invalidServiceResponse);
	               return;
		    }

	        try{
	            fileWriterService.saveCityInfo(cityInfo);
	        }catch(Exception ex){
	            
	            logger.error(invalidFileWriteResponse);
	               return;
	        }
			
		}
	}

	private boolean isValidCity(String city) {
		
		Pattern pattern = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(city);
		boolean invalidPattern = matcher.find();

		if (invalidPattern)
			return false;
		else
			return true;
	}
}
