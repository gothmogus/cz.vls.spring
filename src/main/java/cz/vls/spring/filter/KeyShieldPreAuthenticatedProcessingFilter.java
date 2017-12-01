package cz.vls.spring.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class KeyShieldPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	public KeyShieldPreAuthenticatedProcessingFilter() {
		super();
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return "Anonymous";
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return "N/A";
	}
	
}
