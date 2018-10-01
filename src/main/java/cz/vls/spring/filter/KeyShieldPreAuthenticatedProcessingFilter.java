package cz.vls.spring.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import cz.tdp.kshield.client.KShieldClient;
import cz.tdp.kshield.client.KShieldClientException;
import cz.tdp.kshield.client.UserInfo;

public class KeyShieldPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(KeyShieldPreAuthenticatedProcessingFilter.class);
	
	protected String incomingUser = "";
	
	@Autowired
	protected KShieldClient kshieldClient;
	
	public KeyShieldPreAuthenticatedProcessingFilter() {
	}
	
	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

		logger.info( "***** KeyShieldPreAuthenticatedProcessingFilter >> getPreAuthenticatedCredentials method called *****" );
		
        String[] credentials = new String[2];
	    
		credentials[0] = this.incomingUser;
	    credentials[1] = "user";
	    
	    return credentials;
        
	}


	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		
		logger.info( "***** KeyShieldPreAuthenticatedProcessingFilter >> getPreAuthenticatedPrincipal method called *****" );
		
		String userIPAddress = request.getRemoteAddr();
		
		String principal = kShieldExtractor(userIPAddress); 
		
		return principal; 
		
	}

	private String kShieldExtractor(String userIPAddress) {
		// TODO ugly hack inspection on localhost
        if ( "0:0:0:0:0:0:0:1".equals(userIPAddress) ) {
        	userIPAddress = "10.9.0.22";
        }
		
		logger.info( "***** KeyShieldPreAuthenticatedProcessingFilter =>>  Remote addr  <<= " + userIPAddress + " *****" );
		
		UserInfo user = null;
		
		try {
			
			user = kshieldClient.getUserByIP( userIPAddress );
			
		} catch (KShieldClientException e) {
			
			logger.error( "***** KeyShieldPreAuthenticatedProcessingFilter =>>  EXCEPTION  <<= " + e.getLocalizedMessage() + " *****" );
			
		}
		
		if ( user != null ) {
			
			logger.info( "***** KeyShieldPreAuthenticatedProcessingFilter =>>  User  			<<= " + user.toString()  + " *****" );
			logger.info( "***** KeyShieldPreAuthenticatedProcessingFilter =>>  Extracting screen name  	<<= " + user.getScreenName()  + " *****" );
			
			this.incomingUser = user.getScreenName();
			
			return user.toString();
			
		} else {
		
			return null;
		}
	}

	
}
