package cz.vls.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class[] getRootConfigClasses() {
		return null; 
	}

	@Override
	protected Class[] getServletConfigClasses() {
		return new Class[] { AppMssqlConfig.class, AppOracleConfig.class, WebSecurityConfig.class, ThymeleafConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}