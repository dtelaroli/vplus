package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.util.TestUtil;

public class DefaultDBLoadTest {

	DefaultDBLoad loadDAO;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
		
		loadDAO = new DefaultDBLoad(testUtil.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	@Test
	public void shouldReturnFirstEntity() throws VPlusException {
		MyEntity my = loadDAO.of(MyEntity.class).find(1L);
		assertThat(my.getId(), equalTo(1L));
		assertThat(my.getLabel(), equalTo("Entity 1"));
	}
	
}