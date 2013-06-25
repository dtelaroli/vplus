package org.vplus.core.conf;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBPropertiesTest {

	DBProperties properties;
	
	@Before
	public void setUp() throws Exception {
		properties = DBProperties.create().load();
	}

	@After
	public void tearDown() throws Exception {
		properties = null;
	}

	@Test
	public void shouldLoadProperties() throws IOException {
		assertThat(properties.getProperties(), notNullValue());
		
		properties = DBProperties.create(DBProperties.DEFAULT_DBPROPERTIES).load();
		assertThat(properties.getProperties(), notNullValue());
	}
	
	@Test(expected = FileNotFoundException.class)
	public void shouldDispachErrorWithFileNotExists() throws IOException {
		DBProperties.create("/bla.properties").load();
	}
	
	@Test
	public void shouldReturnHsqldb() {
		assertThat(properties.getDbProperty(), equalTo("hsqldb"));
	}
	
}