package org.vplus.core.crud;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.util.TestUtil;

public class DefaultDBDeleteTest {

	DefaultDBDelete delete;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
		
		delete = new DefaultDBDelete(testUtil.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	@Test
	public void shouldSetClassType() {
		assertThat(
				delete.of(MyEntity.class).type().isAssignableFrom(MyEntity.class), 
				is(true)
		);
	}

	@Test
	public void shouldDeleteFirstEntity() {
		testUtil.beginTransaction();
		MyEntity my = new MyEntity();
		assertThat(my.getId(), nullValue());
		my.name = "New Item";
		my.setId(1L);
		
		delete.of(MyEntity.class).delete(my);
		testUtil.commit();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		delete.delete(null);
	}

}
