package cz.vls.spring.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Component;

import cz.tdp.kshield.client.KShieldClient;
import cz.vls.spring.services.UserDetailsServiceImpl;

@Component
public class KeyShieldAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger( KeyShieldAuthenticationProvider.class );
	
	public KeyShieldAuthenticationProvider() {
		 logger.info( "***** KeyShieldAuthenticationProvider created *****" );
		 
		 setPreAuthenticatedUserDetailsService( new UserDetailsServiceImpl() );
		 
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info( "***** KeyShieldAuthenticationProvider >> authenticate method called *****" );
		
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        if ( name != null ) {
  
            // use the credentials
            // and authenticate against the third-party system
        	return new UsernamePasswordAuthenticationToken(name, password);
        	
        } else {
        	
            return null;
            
        }
	}

	/*@Override
	public boolean supports(Class<?> authentication) {
		boolean ret = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		return ret;
	}*/
	
	/*
	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
	*/
}
