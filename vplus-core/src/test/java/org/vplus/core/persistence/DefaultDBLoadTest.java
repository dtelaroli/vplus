package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.persistence.DefaultDBLoad;
import org.vplus.core.persistence.MyEntity;
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
	public void shouldSetClassType() {
		assertThat(
				loadDAO.of(MyEntity.class).type().isAssignableFrom(MyEntity.class), 
				is(true)
		);
	}

	@Test
	public void shouldReturnFirstEntity() {
		MyEntity my = loadDAO.of(MyEntity.class).find(1L);
		assertThat(my.getId(), equalTo(1L));
		assertThat(my.getLabel(), equalTo("Entity 1"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		loadDAO.find(1L);
	}

}
