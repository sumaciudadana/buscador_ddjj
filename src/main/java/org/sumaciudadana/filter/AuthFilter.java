package org.sumaciudadana.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);

			// Validations
			boolean isLogged = ses != null
					&& ses.getAttribute("username") != null;
			boolean isLoginPage = req.getRequestURI().indexOf("/login.xhtml") >= 0;
			boolean isResource = req.getRequestURI().contains(
					"javax.faces.resource");

			// allow user to proccede if url is login.xhtml or user logged in or
			// user is accessing any page in //public folder
			if (isLoginPage || isLogged || isResource) {
				chain.doFilter(request, response);
			} else {
				// user didn't log in but asking for a page that is not allowed
				// so take user to login page
				res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
