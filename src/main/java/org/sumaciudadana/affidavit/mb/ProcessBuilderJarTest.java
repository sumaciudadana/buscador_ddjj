package org.sumaciudadana.affidavit.mb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class ProcessBuilderJarTest {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		String pentahoPath = "/Users/v619297/Documents/Cesar/dataload-0.0.1-SNAPSHOT.jar";

		String[] arguments = { "java", "-jar", pentahoPath };
		ProcessBuilder pb = new ProcessBuilder(arguments);
		pb.directory(new File("/Users/v619297/Documents/Cesar/"));

		System.out.println("command: "+pb.command());

		System.out.println("run jar " + new Date());
		Process process = pb.start();
		// TODO check what returns in error
		System.out.println("wait for " + new Date());
		int errorCode = process.waitFor();

		System.out.println("Pentaho executed, any errors? "
				+ (errorCode == 0 ? "No" : "Yes"));
		System.out.println("Pentaho output:\n"
				+ output(process.getInputStream()));
		System.out.println("Pentaho error:\n"
				+ output(process.getErrorStream()));

	}

	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

}
