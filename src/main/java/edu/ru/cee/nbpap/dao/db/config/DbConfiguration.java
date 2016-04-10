package edu.ru.cee.nbpap.dao.db.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class DbConfiguration {
	
	@Value("$db{connection.provider_class}")
	private String dbDriverClass;
	@Value("$db{connection.url}")
	private String dbUrl;
	@Value("$db{connection.username}")
	private String dbUser;
	@Value("$db{connection.password}")
	private String dbPassword;
	@Value("$db{connection.pool.maxwait}")
	private Integer dbMaxWait;
	@Value("$db{connection.pool.maxactive}")
	private Integer dbMaxActive;
	@Value("$db{connection.pool.maxidle}")
	private Integer dbMaxIdle;
	
	@Value("$db{hibernate.dialect}")
	private String hibernateDialect;
	@Value("$db{hibernate.show_sql}")
	private String showSql;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dbDriverClass);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		ds.setMaxWaitMillis(dbMaxWait);
		ds.setMaxTotal(dbMaxActive);
		ds.setMaxIdle(dbMaxIdle);
		return ds;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory hibernateSessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(hibernateSessionFactory);
		return htm;
	}
	
	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory hibernateSessionFactory) {
		HibernateTemplate ht = new HibernateTemplate();
		ht.setSessionFactory(hibernateSessionFactory);
		return ht;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", showSql);
		return properties;
	}
	
	@Bean
	public AnnotationSessionFactoryBean hibernateSessionFactory(DataSource dataSource, Properties hibernateProperties) {
		AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
		asfb.setDataSource(dataSource);
		asfb.setHibernateProperties(hibernateProperties);
		asfb.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return asfb;
	}

}
