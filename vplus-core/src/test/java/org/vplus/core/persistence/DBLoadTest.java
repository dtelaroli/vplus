package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.util.TestUtil;

public class DBLoadTest {

	DBLoad loadDAO;
	private TestUtil test;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		
		loadDAO = new DBLoad(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	@Test
	public void shouldReturnFirstEntity() throws CrudException {
		MyEntity my = (MyEntity) loadDAO.find(new MyEntity(1L));
		assertThat(my.getId(), equalTo(1L));
		assertThat(my.name(), equalTo("João"));
	}
	
}