package org.sumaciudadana.affidavit.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.sumaciudadana.affidavit.service.LoginService;
import org.sumaciudadana.affidavit.util.SessionUtils;
import org.sumaciudadana.affidavit.util.StringUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginService}")
	private LoginService loginService;

	private String password, message, uname;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String loginProject() {
		// Get current session
		HttpSession session = SessionUtils.getSession();
		Boolean success = false;

		if (StringUtils.validInput(uname) && StringUtils.validInput(password)) {
			success = loginService.validCredentials(uname, password);
		} 

		if (success) {
			// Store uname and pass in session
			session.setAttribute("username", uname);
			return "carga.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Credenciales invalidas!", "Intente nuevamente!"));

			// invalidate session, and redirect to other pages
			session.invalidate();
			return "login.xhtml";
		}

	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml";
	}

}
