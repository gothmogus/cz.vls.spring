package cz.vls.spring.providers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import cz.tdp.kshield.client.KShieldClient;
import cz.tdp.kshield.client.KShieldClientException;
import cz.tdp.kshield.client.UserInfo;

@Component
public class KeyShieldAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger( KeyShieldAuthenticationProvider.class );
	
	@Autowired
	protected KShieldClient kshieldClient;
	
	public KeyShieldAuthenticationProvider() {
		 logger.info( "***** KeyShieldAuthenticationProvider created *****" );
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info( "***** KeyShieldAuthenticationProvider >> authenticate method called *****" );
		
		// Get the IP address of the user trying to use the site
		WebAuthenticationDetails  wad = (WebAuthenticationDetails) authentication.getDetails();
        String userIPAddress = wad.getRemoteAddress();
        
        // TODO ugly hack inspection on localhost
        if ( "0:0:0:0:0:0:0:1".equals(userIPAddress) ) {
        	userIPAddress = "10.9.0.22";
        }
		
		logger.info( "***** KeyShieldAuthenticationProvider >>  Remote addr  <<= " + userIPAddress + " ******" );
		
		UserInfo user = null;
		
		try {
			
			user = kshieldClient.getUserByIP( userIPAddress );
			
		} catch (KShieldClientException e) {
			
			logger.debug( "***** KeyShieldAuthenticationProvider =>>  EXCEPTION  <<= " + e.getLocalizedMessage() + " ******" );
			
		}
		
		if ( user != null ) {
			
			logger.info( "***** KeyShieldAuthenticationProvider >>  User  <<= " + user.toString()  + " ******" );
			
			return new UsernamePasswordAuthenticationToken( user.getUsername(), "" );
			
		} else {
		
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean ret = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		return ret;
	}

}
