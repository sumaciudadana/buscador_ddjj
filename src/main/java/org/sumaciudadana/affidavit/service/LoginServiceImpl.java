package org.sumaciudadana.affidavit.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.LoginDAO;
import org.sumaciudadana.affidavit.entity.User;

@Transactional
@Service
public class LoginServiceImpl implements LoginService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2659568599390801033L;
	
	
	@Autowired
	LoginDAO loginDAO;

	@Override
	public boolean validCredentials(String username, String pwd) {
		return loginDAO.validateCredentials(username, pwd);
	}
	
	public User userExists(String username){
		return loginDAO.userExists(username);
	}

}
