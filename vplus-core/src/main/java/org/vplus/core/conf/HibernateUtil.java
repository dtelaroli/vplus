package org.vplus.core.conf;


import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static DBConfig config;
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public static Session getSession() throws Exception {
		
		criaFactory();
			
		return sessionFactory.openSession();
	}

	private static void criaFactory() throws Exception {
		
		if (sessionFactory == null) {
			
			config = new DBConfig(null);
			
			Configuration configure = new Configuration()
				.configure()
				.setProperty("hibernate.connection.driver_class", config.getDriver())
				.setProperty("hibernate.dialect", config.getDialect())
				.setProperty("hibernate.connection.url", config.getHost())
				.setProperty("hibernate.connection.username", config.getUser())
				.setProperty("hibernate.connection.password", config.getPassword())
				.setProperty("hibernate.hbm2ddl.auto", "update")
				.setProperty("hibernate.show_sql", "true")
				.setProperty(
						"hibernate.connection.provider_class",
						"org.hibernate.service.jdbc.connections.internal.DriverManagerConnectionProviderImpl"
				)
				.setProperty("hibernate.cache.use_second_level_cache", "false")
				.setProperty("hibernate.search.default.indexBase", "/tmp/lucene")
				;
			
			serviceRegistry = new ServiceRegistryBuilder()
								.applySettings(configure.getProperties())
								.buildServiceRegistry();        
			
			sessionFactory = configure.buildSessionFactory(serviceRegistry);
		}
	}

	public static Connection getConnection() throws Exception {
		Class.forName(config.getDriver());
		
		Connection connection = DriverManager.getConnection(
			config.getHost(), config.getUser(), config.getPassword()
		);
		return connection;
	}

}
