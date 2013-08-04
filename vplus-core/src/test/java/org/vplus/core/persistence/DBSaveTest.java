package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.generics.Status;
import org.vplus.core.util.TestUtil;

public class DBSaveTest {

	DBSave save;
	private TestUtil test;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		
		save = new DBSave(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	@Test
	public void shouldSaveEntity() throws Exception {
		test.beginTransaction();
		MyEntity my = new MyEntity("New Item");
		assertThat(my.getId(), nullValue());
		
		my = (MyEntity) save.persist(my);
		test.commit();
		
		assertThat(my.getId(), notNullValue());
		assertThat(my.name(), equalTo("New Item"));
	}
	
	@Test
	public void shouldEditEntity() throws Exception {
		test.beginTransaction();
		MyEntity my = new MyEntity("New Item");
		assertThat(my.getId(), nullValue());
		
		my = (MyEntity) save.persist(my);
		assertThat(my.createdAt(), equalTo(my.modifiedAt()));
		
		my.withStatus(Status.Removed);
		
		my = (MyEntity) save.persist(my);
		assertThat(my.createdAt(), not(equalTo(my.modifiedAt())));
		
		test.commit();
		
		assertThat(my.getId(), notNullValue());
		assertThat(my.name(), equalTo("New Item"));
	}
	
}
