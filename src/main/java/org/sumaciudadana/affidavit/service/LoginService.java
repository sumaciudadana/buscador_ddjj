package org.sumaciudadana.affidavit.service;

import org.sumaciudadana.affidavit.entity.User;

public interface LoginService {
	
	public boolean validCredentials(String username,String pwd);
	public User userExists(String username);

}
