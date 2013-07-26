package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.util.TestUtil;

public class DBLoadTest {

	DBLoad loadDAO;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
		
		loadDAO = new DBLoad(testUtil.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	@Test
	public void shouldReturnFirstEntity() throws VPlusException {
		MyEntity my = (MyEntity) loadDAO.of(MyEntity.class).find(new MyEntity(1L));
		assertThat(my.getId(), equalTo(1L));
		assertThat(my.getLabel(), equalTo("Entity 1"));
	}
	
}