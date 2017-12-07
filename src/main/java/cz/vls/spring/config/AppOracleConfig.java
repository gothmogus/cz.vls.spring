package cz.vls.spring.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cz.vls.spring.model.mssql.User;
import cz.vls.spring.model.oracle.OrganizacniJednotka;
import oracle.jdbc.pool.OracleDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cz.vls.spring")
@EntityScan(basePackages = "cz.vls.spring.model.oracle")
@EnableJpaRepositories(basePackages = "cz.vls.spring.repositories.oracle",
			entityManagerFactoryRef = "oracleEntityManagerFactory",
			transactionManagerRef = "oracleTransactionManager")
@PropertySources({ @PropertySource("classpath:application.properties") })
public class AppOracleConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;
	
	@Bean
    public DataSource oracleDataSource() throws SQLException
    {
        /*EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType( EmbeddedDatabaseType.HSQL ).build();*/
		
		OracleDataSource dataSource = new OracleDataSource();
		 
        // See: datasouce-cfg.properties
        dataSource.setURL(env.getProperty("oracle.datasource.url"));
        dataSource.setUser(env.getProperty("oracle.datasource.username"));
        dataSource.setPassword(env.getProperty("oracle.datasource.password"));
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
 
        System.out.println("## getDataSource: " + dataSource);
 
        return dataSource;
		
    }
	
    @Bean
    public EntityManagerFactory oracleEntityManagerFactory() throws SQLException
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl( true );

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( vendorAdapter );
        
        Properties jpaProperties = new Properties();
        
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("oracle.jpa.hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                env.getRequiredProperty("oracle.jpa.hibernate.ddl-auto")
        );
 
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", 
                env.getRequiredProperty("oracle.jpa.show-sql")
        );
        
        factory.setJpaProperties(jpaProperties);
        factory.setPackagesToScan( OrganizacniJednotka.class.getPackage().getName() );
        
        factory.setDataSource( oracleDataSource() );
        factory.afterPropertiesSet();

        return factory.getObject();
    }
    
    @Bean
    public PlatformTransactionManager oracleTransactionManager() throws SQLException
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory( oracleEntityManagerFactory() );
        return txManager;
    }
	
	// Gothmog - can add interceptor for Loggin, Security etc..
	/*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new HelloWorldInterceptor() );
    }*/
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}


