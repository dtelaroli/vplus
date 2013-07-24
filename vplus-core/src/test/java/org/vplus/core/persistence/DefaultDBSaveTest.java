package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.persistence.DefaultDBSave;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.util.TestUtil;

public class DefaultDBSaveTest {

	DefaultDBSave save;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
		
		save = new DefaultDBSave(testUtil.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	@Test
	public void shouldSetClassType() {
		assertThat(
				save.of(MyEntity.class).type().isAssignableFrom(MyEntity.class), 
				is(true)
		);
	}

	@Test
	public void shouldFirstEntity() {
		testUtil.beginTransaction();
		MyEntity my = new MyEntity();
		assertThat(my.getId(), nullValue());
		my.name = "New Item";
		
		my = save.of(MyEntity.class).persist(my);
		testUtil.commit();
		
		assertThat(my.getId(), notNullValue());
		assertThat(my.getLabel(), equalTo("New Item"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		save.persist(null);
	}

}
