package org.vplus.core.conf;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBConfigTest {

	DBConfig config;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		config = null;
	}

	@Test
	public void propertiesShouldBeNullWhenEmptyConstructorOrFalseArg() throws IOException {
	}
	
	@Test
	public void shouldLoadProperties() {
		assertThat(config.getProperties(), notNullValue());
	}
	
	@Test
	public void deveRetornarDbName() {
	}

}
