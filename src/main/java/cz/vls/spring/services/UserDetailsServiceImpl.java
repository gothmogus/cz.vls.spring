package cz.vls.spring.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class UserDetailsServiceImpl implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
		UserDetails userDetails = null;

	    String[] credentials = (String[]) token.getPrincipal();
	    boolean principal = Boolean.valueOf(token.getCredentials().toString());

	    if (credentials != null && principal == true) {
	    	
	        String name = credentials[0];
            userDetails = getUser(name);
            
	    }

	    if (userDetails == null) {
	        throw new UsernameNotFoundException("Could not load user : "
	                + token.getName());
	    }

	    return userDetails;
	}
	
	private UserDetails getUser(String username) {
	    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//	    grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_RETAILER"));
//	    grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
	    return new User(username, "notused", true, true, true, true,
	            grantedAuthorities);
	}

	

}
