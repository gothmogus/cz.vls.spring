package cz.vls.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import cz.tdp.kshield.client.KShieldClient;
import cz.vls.spring.filter.KeyShieldPreAuthenticatedProcessingFilter;
import cz.vls.spring.providers.KeyShieldAuthenticationProvider;
import cz.vls.spring.providers.SOAAuthenticationProvider;

@Configuration
@ComponentScan(basePackages = "cz.vls.spring.providers")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	*/

	@Bean
    public KShieldClient kshieldClient() throws Exception {
    	return new KShieldClient( "https://swb.vlscr.local:8486" );
    }
	
	@Autowired
    private KeyShieldAuthenticationProvider keyShieldAuthenticationProvider;
	
	@Autowired
	private SOAAuthenticationProvider soaAuthenticationProvider; 
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.keyShieldAuthenticationProvider).authenticationProvider(soaAuthenticationProvider);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.addFilterBefore(kshieldFilter(), AbstractPreAuthenticatedProcessingFilter.class)
        .authenticationProvider(keyShieldAuthenticationProvider).csrf().disable()
        .authorizeRequests().anyRequest().authenticated();
    	
    	/*http
        .authorizeRequests()
        .antMatchers("/organization/people/**").permitAll()
        .antMatchers("/organization/units").authenticated().and().httpBasic().disable();*/
//        
        
    }
    
    @Bean
    public KeyShieldPreAuthenticatedProcessingFilter kshieldFilter() throws Exception {
    	KeyShieldPreAuthenticatedProcessingFilter filter = new KeyShieldPreAuthenticatedProcessingFilter();
    	filter.setAuthenticationManager(authenticationManager());
    	return filter;
	}
    
    @Autowired
	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
        .password("admin").roles("ADMIN");
	}
}
