package com.goeuro.testapp.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.goeuro.testapp.model.CityInfo;

@Service
@PropertySource("application.properties")
public class FileWriterService {
	
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String COMMA_DELIMITER = ",";

	 @Value("${goeuro.cityinfo.file.fileheader}")
	    private String FILE_HEADER;

	 @Value("${goeuro.cityinfo.file.filepath.directory}")
	    private String directory;

	 @Value("${goeuro.cityinfo.file.filename}")
	    private String filename;

    public void saveCityInfo(List<CityInfo> cityInfo) throws Exception{
		String absoluteFilePath = getFilePath();
		FileWriter fileWriter = null;
		
		synchronized (this) {

		      try{
		            boolean isNewFile = isNewFileCreated(absoluteFilePath);
		        fileWriter = new FileWriter(absoluteFilePath,true);

		        if(isNewFile) {
		            fileWriter.append(FILE_HEADER);
		            fileWriter.append(NEW_LINE_SEPARATOR);
		        }
		            for (CityInfo city : cityInfo) {
		                fileWriter.append(city.get_id());
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(city.getName());
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(city.getType());
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(city.getGeo_position().getLatitude());
		                fileWriter.append(COMMA_DELIMITER);
		                fileWriter.append(city.getGeo_position().getLongitude());

		                fileWriter.append(NEW_LINE_SEPARATOR);
		                
		            }
		        }catch(Exception e){
		            throw e;
		        }
		        finally{
		            fileWriter.flush();
		            fileWriter.close();
		        }

        }
	}

	private boolean isNewFileCreated(String absoluteFilePath) throws IOException {
		File f = new File(absoluteFilePath);
		return f.createNewFile();
	}

	private String getFilePath() {
		String workingDirectory = System.getProperty(directory);
		String absoluteFilePath = workingDirectory + File.separator + filename ;
		return absoluteFilePath;
	}

}
