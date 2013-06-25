package org.vplus.core.conf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {

	public static final String DEFAULT_DBPROPERTIES = "/dbtest.properties";
	
	private Properties properties;
	private String db;
	private String file;

	private DBProperties() {
		properties = new Properties();
		db = getDbProperty();
	}
	
	public static DBProperties create() {
		return create(DEFAULT_DBPROPERTIES);
	}
	
	public static DBProperties create(String fileName) {
		DBProperties properties = new DBProperties();
		properties.file = fileName;
		return properties;
	}
	
	public DBProperties load() throws IOException {
		loadProperties();
		return this;
	}
	
	private void loadProperties() throws IOException {
		InputStream stream = getClass().getResourceAsStream(file);
		if(stream == null) {
			throw new FileNotFoundException(file);
		}
		properties.load(stream);
	}

	public String getDriverProperty() {
		return properties.getProperty(String.format("test.connection.%s.driver", db));
	}

	public String getDialectProperty() {
		return properties.getProperty(String.format("test.connection.%s.dialect", db));
	}

	public String getHostProperty() {
		return properties.getProperty(String.format("test.connection.%s.host", db));
	}

	public String getPassProperty() {
		return properties.getProperty(String.format("test.connection.%s.password", db));
	}

	public String getUserProperty() {
		return properties.getProperty(String.format("test.connection.%s.user", db));
	}

	public String getDbProperty() {
		return properties.getProperty("test.dbUnit.database");
	}

	protected Properties getProperties() {
		return properties;
	}

	protected String getDb() {
		return db;
	}

	protected String getFile() {
		return file;
	}
	
}
