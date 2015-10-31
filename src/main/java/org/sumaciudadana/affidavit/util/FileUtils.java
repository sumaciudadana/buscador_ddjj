package org.sumaciudadana.affidavit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	public static final String destination = "\\temp\\upload";

	public static String copyFile(String fileName, InputStream in) {
		String save = "";
		int read = 0;
		
		File f = new File(destination);
		if (!f.isDirectory()) {
			f.mkdir();
		}
		
		try {
			save = destination.concat(File.separator).concat(fileName);
			FileOutputStream fos = new FileOutputStream(save);
			
			while ((read = in.read()) >= 0) {
				fos.write(read);
			}
			
			in.close();
			fos.flush();
			fos.close();
			
			return save;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
	}

}
