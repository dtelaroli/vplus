package org.vplus.core.jpa;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPAUtilTest {

	JPAUtil jpa;
	
	@Before
	public void setUp() throws Exception {
		jpa = new JPAUtil();
	}
	
	@After
	public void tearDown() {
		jpa.destroy();
	}

	@Test
	public void shouldReturnPersistenceUnitName() {
		jpa.withUnit("test");
		assertThat(jpa.unit, notNullValue());
	}
	
	@Test
	public void shouldReturnEntityManager() {
		EntityManager em = jpa.withUnit("test").entityManager();
		assertThat(em, notNullValue());
	}
	
	@Test(expected = PersistenceException.class)
	public void shouldDispatchErrorOnBuildWithoutInvokeWithUnit() {
		EntityManager em = jpa.entityManager();
		assertThat(em, notNullValue());
	}
	
	@Test
	public void shouldReturnConnection() throws SQLException {
		Connection conn = jpa.withUnit("test").connection();
		assertThat(conn.isClosed(), notNullValue());
	}

}