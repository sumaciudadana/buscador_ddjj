package org.sumaciudadana.affidavit.util;

public class TextUtils {
	
	public static String formatMuni(String name){
		name = name.replace("&Ntilde;","�");
		return name;
	}

}
