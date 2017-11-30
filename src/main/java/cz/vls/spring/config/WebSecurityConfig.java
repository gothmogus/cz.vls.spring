package cz.vls.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cz.vls.spring.providers.KeyShieldAuthenticationProvider;

@Configuration
@ComponentScan(basePackages = "cz.vls.spring.providers")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	*/
	
	@Autowired
    private KeyShieldAuthenticationProvider keyShieldAuthenticationProvider;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.keyShieldAuthenticationProvider);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers("/organization/units").permitAll()
        .antMatchers("/organization/people/**").permitAll()
        .anyRequest().authenticated();
    }
    
    @Autowired
	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
        .password("admin").roles("ADMIN");
	}
}
