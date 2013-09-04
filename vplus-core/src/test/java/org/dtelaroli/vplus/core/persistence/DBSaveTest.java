package org.dtelaroli.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import org.dbunit.DatabaseUnitException;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		assertThat(my.createdAt().get(Calendar.SECOND), equalTo(my.createdAt().get(Calendar.SECOND)));
		
		my.withStatus(Status.Removed);
		
		Thread.sleep(2000);
		
		my = (MyEntity) save.persist(my);
		assertThat(my.createdAt().get(Calendar.SECOND), not(equalTo(my.modifiedAt().get(Calendar.SECOND))));
		
		test.commit();
		
		assertThat(my.getId(), notNullValue());
		assertThat(my.name(), equalTo("New Item"));
	}
	
}
