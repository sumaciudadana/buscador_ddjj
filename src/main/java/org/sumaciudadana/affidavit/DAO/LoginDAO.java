package org.sumaciudadana.affidavit.DAO;

import org.sumaciudadana.affidavit.entity.User;

public interface LoginDAO {
	
	public boolean validateCredentials(String username, String pwd);
	public User userExists(String username);

}
