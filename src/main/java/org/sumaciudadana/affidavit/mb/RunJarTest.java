package org.sumaciudadana.affidavit.mb;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RunJarTest {

	public static void main(String[] args) {
		String pentahoPath = "/Users/v619297/Documents/Cesar/dataload-0.0.1-SNAPSHOT.jar";
		File workingDir = new File("/Users/v619297/Documents/Cesar/");

		try {
			Process proc = Runtime.getRuntime().exec(
					"java -jar " + pentahoPath, null, workingDir);

			BufferedReader inputReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			
			String line;
			while ((line = inputReader.readLine()) != null) {
				System.out.println(line);
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
			
			System.out.println(errorMessage.toString());
			System.out.println("error: "+errorMessage.toString().length());
			
			

		} catch (Exception e) {
			System.out.println("ERROR DETECTED");
			e.printStackTrace();
		}

	}

}
