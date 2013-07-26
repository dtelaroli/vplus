package org.vplus.core.util;

import static org.hamcrest.Matchers.is;
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
		jpa = new JPAUtil("test");
	}
	
	@After
	public void tearDown() {
		jpa.close();
	}

	@Test
	public void shouldReturnPersistenceUnitName() {
		jpa = new JPAUtil();
		jpa.withUnit("test");
		assertThat(jpa.unit, notNullValue());
	}
	
	@Test
	public void shouldReturnEntityManager() {
		EntityManager em = jpa.entityManager();
		assertThat(em, notNullValue());
	}
	
	@Test(expected = PersistenceException.class)
	public void shouldDispatchErrorOnBuildWithoutInvokeWithUnit() {
		jpa.withUnit("").entityManager();
	}
	
	@Test
	public void shouldReturnConnection() throws SQLException {
		Connection conn = jpa.connection();
		assertThat(conn.isClosed(), notNullValue());
	}
	
	@Test
	public void shouldStartATransaction() {
		jpa.beginTransaction();
		assertThat(jpa.entityManager().getTransaction().isActive(), is(true));
	}
	
	@Test
	public void shouldCommitATransaction() {
		jpa.beginTransaction();
		assertThat(jpa.entityManager().getTransaction().isActive(), is(true));
		
		jpa.commit();
		assertThat(jpa.entityManager().getTransaction().isActive(), is(false));
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldDispatchErrorIfTransactionIsInactiveOnCommit() {
		jpa.commit();
	}
	
	@Test
	public void shouldRollbackATransaction() {
		jpa.beginTransaction();
		assertThat(jpa.entityManager().getTransaction().isActive(), is(true));
		
		jpa.rollback();
		assertThat(jpa.entityManager().getTransaction().isActive(), is(false));
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldDispatchErrorIfTransactionIsInactiveOnRollback() {
		jpa.rollback();
	}

}