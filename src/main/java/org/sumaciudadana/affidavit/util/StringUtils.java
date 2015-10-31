package org.sumaciudadana.affidavit.util;

public class StringUtils {
	
	public static String formatMuni(String name){
		name = name.replace("&Ntilde;","Ñ");
		return name;
	}
	
	public static boolean validInput(String string){
		if (string!=null && !string.equals("")) {
			return true;
		}else{
			return false;
		}
	}

}
