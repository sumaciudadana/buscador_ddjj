package org.sumaciudadana.affidavit.service;

import java.io.IOException;
import java.util.List;

import org.sumaciudadana.affidavit.entity.Filelog;
import org.sumaciudadana.affidavit.entity.User;

public interface FilelogService {
	
	public String callPentaho(String fileUniqueName) throws IOException, InterruptedException;
	public void saveFile(String fileName, String fileUniqueName, User user) throws Exception;
	public List<Filelog> loadFiles() throws Exception;
	public Filelog getFileLog(int id) throws Exception;

}
