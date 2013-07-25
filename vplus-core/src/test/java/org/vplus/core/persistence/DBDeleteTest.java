package org.vplus.core.persistence;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.util.TestUtil;

public class DBDeleteTest {

	DBDelete delete;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
		
		delete = new DBDelete(testUtil.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	@Test
	public void shouldDeleteFirstEntity() {
		testUtil.beginTransaction();
		MyEntity my = new MyEntity();
		assertThat(my.getId(), nullValue());
		my.name = "New Item";
		my.setId(1L);
		
		delete.delete(my);
		testUtil.commit();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		delete.delete(null);
	}

}
