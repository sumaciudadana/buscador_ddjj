package org.sumaciudadana.affidavit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.LoginDAO;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDAO loginDAO;

	@Override
	public boolean validCredentials(String username, String pwd) {
		return loginDAO.validateCredentials(username, pwd);
	}

}
