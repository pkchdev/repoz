package repoz.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceProperties());
		sessionFactory.setPackagesToScan("repoz.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSourceProperties() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(environment.getRequiredProperty("app.datasource.driver"));
	    dataSource.setUrl(environment.getRequiredProperty("app.datasource.url"));
	    dataSource.setUsername(environment.getRequiredProperty("app.datasource.username"));
	    dataSource.setPassword(environment.getRequiredProperty("app.datasource.password"));
	    return dataSource;
	}
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("app.hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("app.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("app.hibernate.format_sql"));
        return properties;        
    }
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory session) {
       HibernateTransactionManager manager = new HibernateTransactionManager();
       manager.setSessionFactory(session);
       return manager;
    }
}
