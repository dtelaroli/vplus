package org.vplus.core.conf;

import java.io.IOException;
import java.util.Properties;

public class DBConfig {

	private String driver;
	private String dialect;
	private String host;
	private String user;
	private String password;
	private Properties properties;

	public DBConfig(DBProperties properties) throws IOException {
		user = properties.getUserProperty();
		password = properties.getPassProperty();
		host = properties.getHostProperty();
		dialect = properties.getDialectProperty();
		driver = properties.getDriverProperty();
	}

	public String getDriver() {
		return driver;
	}

	public String getDialect() {
		return dialect;
	}

	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	protected Properties getProperties() {
		return properties;
	}
	
}