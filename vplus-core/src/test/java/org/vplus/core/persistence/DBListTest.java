package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.util.TestUtil;

public class DBListTest {

	DBList listDAO;
	private TestUtil test;
	
	@Before
	public void setUp() throws Exception {
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		listDAO = new DBList(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	@Test
	public void shouldReturn3ItemsFromDBUnit() throws VPlusException {
		List<Model> list = listDAO.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(3));
	}
}