package cz.vls.spring.config;

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
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import cz.vls.spring.model.mssql.User;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cz.vls.spring")
@EntityScan(basePackages = "cz.vls.spring.model.mssql")
@EnableJpaRepositories(basePackages = "cz.vls.spring.repositories.mssql",
			entityManagerFactoryRef = "mssqlEntityManagerFactory",
			transactionManagerRef = "mssqlTransactionManager")
@PropertySources({ @PropertySource("classpath:application.properties") })
public class AppMssqlConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;
	
	@Bean
	@Primary
    public DataSource mssqlDataSource()
    {
		
		BasicDataSource dataSource = new BasicDataSource();
		 
        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
 
        System.out.println("## getDataSource: " + dataSource);
 
        return dataSource;
		
    }
	
    @Bean
    @Primary
    public EntityManagerFactory mssqlEntityManagerFactory()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl( true );

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( vendorAdapter );
        factory.setPackagesToScan( User.class.getPackage().getName() );
        
        Properties jpaProperties = new Properties();
        
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                env.getRequiredProperty("spring.jpa.hibernate.ddl-auto")
        );
 
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", 
                env.getRequiredProperty("spring.jpa.show-sql")
        );
        
        factory.setJpaProperties(jpaProperties);
        
        factory.setDataSource( mssqlDataSource() );
        factory.afterPropertiesSet();

        return factory.getObject();
    }
    
    @Bean
    @Primary
    public PlatformTransactionManager mssqlTransactionManager()
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory( mssqlEntityManagerFactory() );
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


/*public class AppConfig {

}
*/



