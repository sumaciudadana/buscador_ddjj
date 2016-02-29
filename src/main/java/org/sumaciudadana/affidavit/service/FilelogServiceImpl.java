package org.sumaciudadana.affidavit.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.FileLogDAO;
import org.sumaciudadana.affidavit.entity.Filelog;
import org.sumaciudadana.affidavit.entity.User;

@Transactional
@Service
public class FilelogServiceImpl implements FilelogService{
	
	private final static Logger LOGGER = Logger.getLogger(FilelogServiceImpl.class.getName());
	
	// TODO load this from property
	//private static final String WORKING_DIRECTORY = "/Users/v619297/Documents/Cesar/";
	private static final String WORKING_DIRECTORY = "/home/ubuntu/suma/";
	private static final String LOAD_JAR = "dataload-0.0.1-SNAPSHOT.jar";
	
	@Autowired
	FileLogDAO fileLogDAO;

	@Override
	public String callPentaho(String fileUniqueName) throws IOException, InterruptedException {
		
		LOGGER.info("call pentaho method");
		
		if (fileUniqueName.contains(".xls")) {
			fileUniqueName = fileUniqueName.replace(".xls", "");
		}
		
		if (fileUniqueName.contains("/home/ubuntu/suma/files/")) {
			fileUniqueName = fileUniqueName.replace("/home/ubuntu/suma/files/", "");
		}
		
		LOGGER.info("file name: "+fileUniqueName);
		
		String command = "java -jar " + WORKING_DIRECTORY+LOAD_JAR+" "+fileUniqueName;
		
		LOGGER.info("command: "+command);
		
		Process proc = Runtime.getRuntime().exec(command, null, new File(WORKING_DIRECTORY));

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		
		String line;
		while ((line = inputReader.readLine()) != null) {
			LOGGER.info(line);
		}

		StringBuffer errorMessage = new StringBuffer();
		String errorLine;
		int lineCounter = 0;
		while ((errorLine = errorReader.readLine()) != null) {
			if (lineCounter>=2) {
				errorMessage.append(errorLine);
				errorMessage.append(System.getProperty("line.separator"));
			}
			lineCounter++;
		}
		
		proc.waitFor();
		
		return errorMessage.toString();
	}

	@Override
	public void saveFile(String fileName, String fileUniqueName, User user) throws Exception {
		fileLogDAO.saveFile(fileName,fileUniqueName,user);
	}

	public List<Filelog> loadFiles() throws Exception {
		return fileLogDAO.loadFiles();
	}

	@Override
	public Filelog getFileLog(int id) throws Exception {
		return fileLogDAO.getFilelog(id);
	}

}
