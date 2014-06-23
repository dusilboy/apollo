package com.skt.apollo.util;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.reference.DefaultEncoder;


public class CSRFUtil {
	
	private String csrfToken = resetCSRFToken();
	
	public String resetCSRFToken() {
		csrfToken = ESAPI.randomizer().getRandomString(8, DefaultEncoder.CHAR_ALPHANUMERICS);
		return csrfToken;
	}
	
	final static String CSRF_TOKEN_NAME = "cftoken";
	
	public String addCSRFToken(String href) {
		User user = ESAPI.authenticator().getCurrentUser();
		
		String token = CSRF_TOKEN_NAME + "=" + user.getCSRFToken();
		return href.indexOf( '?') != -1 ? href + "&amp;" + token : href + "?" + token;
	}
	
	public String getCSRFToken() {
		User user = ESAPI.authenticator().getCurrentUser();
		
		if ( user == null ) {
			return null;
		}
		
		return user.getCSRFToken();
	}
	
	
	public void verifyCSRFToken(HttpServletRequest request) throws IntrusionException {
		User user = ESAPI.authenticator().getCurrentUser();
		
		if ( request.getAttribute(user.getCSRFToken()) != null ) {
			return ;
		}
		
		String token = request.getParameter(CSRF_TOKEN_NAME);
		
		if ( ! user.getCSRFToken().equals(token) ) {
			throw new IntrusionException("Authentication failed", "Possibly forged HTTP request without proper CSRF token detected");
		}
	}
	
}
