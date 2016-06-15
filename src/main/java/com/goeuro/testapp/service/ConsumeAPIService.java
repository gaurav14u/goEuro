package com.goeuro.testapp.service;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.goeuro.testapp.model.CityInfo;

@Service
@PropertySource("application.properties")
public class ConsumeAPIService {

	@Autowired
	RestTemplate restTemplate;
	
	 @Value("${goeuro.cityinfo.api}")
	    private String cityInfoApi;
	 
	/**
	 * Get response from api.
	 * 
	 * @param city {@link String}
	 * @return {@link List}
	 * @throws UnknownHostException
	 */
	public List<CityInfo> getCityInfo(String city) throws UnknownHostException{
		
	    CityInfo[] result = restTemplate.getForObject(cityInfoApi+city, CityInfo[].class);
	    return Arrays.asList(result);
	    
	}
}
