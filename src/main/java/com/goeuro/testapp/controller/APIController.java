package com.goeuro.testapp.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

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

    @Value("${goeuro.cityinfo.api.connection.error}")
    private String connectionErrorMessage;

    @Value("${goeuro.cityinfo.openfile.error}")
    private String fileNotFoundErrorMessage;

    @Value("${goeuro.cityinfo.inputcity.info.notfound}")
    private String cityInfoNotFound;
    
    
	/**
	 * @param city {@link String}
	 * @throws Exception {@link Exception}
	 */
	public void consumeAPI(String city) throws Exception{
		if(!isValidCity(city)){
		    // send response to user
		    System.out.println(invalidCityName);
		    // log response to file
		    logger.warn(invalidCityName);
		    
		}else{
		    
		    List<CityInfo> cityInfo = null;
		    try{
		        // get response from api
		        cityInfo = consumeAPIService.getCityInfo(city);
		        
		        if(cityInfo.isEmpty()){
		            // send response to user
		            System.out.println(cityInfo);
		            // log response to file
		            logger.warn(cityInfoNotFound);
		            return;
		        }
		        
		    }catch(ResourceAccessException ex){
                handleException(ex, connectionErrorMessage);
                return;
		    }catch(Exception ex){
                handleException(ex, invalidServiceResponse);
                return;
		    }

	        try{
	            // save response to file
	            fileWriterService.saveCityInfo(cityInfo);
	        }catch(FileNotFoundException ex){
                handleException(ex, fileNotFoundErrorMessage);
                return;
            }catch(Exception ex){
                handleException(ex, invalidFileWriteResponse);
	        }
		}
	}

    /**
     * @param ex {@link Exception}
     * @param message {@link String}
     */
    private void handleException(Exception ex, String message) {
        System.out.println(message);
        
        logger.error("Exception occured  "+ex);
    }

	/**
	 * @param city {@link String}
	 * @return {@link Boolean}
	 */
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
