package cz.vls.spring.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SOAAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(SOAAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info( "***** SOAAuthenticationProvider >> authenticate method called *****" );
		
		String name = authentication.getName();
         
        if ( name != null ) {
  
            // use the credentials
            // and authenticate against the third-party system
        	return new UsernamePasswordAuthenticationToken(name, "userPass");
        	
        } else {
        	
            return null;
            
        }
	}

	/*@Override
	public boolean supports(Class<?> authentication) {
		boolean ret = UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		return ret;
	}*/
	
	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
	
}
