package org.dtelaroli.vplus.core.persistence;

import org.dbunit.DatabaseUnitException;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBDeleteTest {

	DBDelete delete;
	private TestUtil test;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		
		delete = new DBDelete(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	@Test
	public void shouldDeleteFirstEntity() {
		test.beginTransaction();
		MyEntity my = new MyEntity(1L);
		
		delete.delete(my);
		test.commit();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		delete.delete(null);
	}

}
