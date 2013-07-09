package org.vplus.core.jpa;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.jpa.JPAUtil;

public class JPAUtilTest {

	JPAUtil jpa;
	
	@Before
	public void setUp() throws Exception {
		jpa = new JPAUtil();
	}

	@After
	public void tearDown() throws Exception {
		jpa = null;
	}

	@Test
	public void shouldReturnServiceRegistry() {
		jpa.withUnit("test");
		assertThat(jpa.unit, notNullValue());
	}
	
	@Test
	public void shouldReturnService() {
		EntityManager em = jpa.withUnit("test").build();
		assertThat(em, notNullValue());
	}
	
	@Test(expected = PersistenceException.class)
	public void shouldDispatchErrorOnBuildWithoutInvokeWithUnit() {
		EntityManager em = jpa.build();
		assertThat(em, notNullValue());
	}

}