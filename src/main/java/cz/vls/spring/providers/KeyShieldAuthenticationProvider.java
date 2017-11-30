package cz.vls.spring.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class KeyShieldAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger( KeyShieldAuthenticationProvider.class );
	
	public KeyShieldAuthenticationProvider() {
		 logger.info( "***** KeyShieldAuthenticationProvider created *****" );
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info( "***** KeyShieldAuthenticationProvider authenticate method called *****" );
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean ret = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		return ret;
	}

}
