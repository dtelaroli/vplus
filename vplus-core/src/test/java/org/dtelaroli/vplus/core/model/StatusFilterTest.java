package org.dtelaroli.vplus.core.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.persistence.DBList;
import org.dtelaroli.vplus.core.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class StatusFilterTest {

	StatusFilter filter;
	private TestUtil test;
	private DBList dbList;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		filter = spy(new StatusFilter(test.entityManager()));
		dbList = new DBList(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
		filter = null;
	}

	@Test
	public void shouldReturnSessionFromEntityManager() {
		assertThat(filter.hibernateSession(), notNullValue());
	}
	
	@Test
	public void shouldEnableFilter() {
		filter.active();
		assertThat(filter.filter(), notNullValue());
	}
	
	@Test
	public void shouldDisableFilter() {
		filter.disableFilter();
		assertThat(filter.filter(), nullValue());
	}
	
	@Test
	public void shouldReturnTrueIfFilterEnabled() {
		filter.active();
		assertThat(filter.isActiveFilter(), equalTo(true));
	}
	
	@Test
	public void shouldReturnFalseIfFilterEnabled() {
		filter.disableFilter();
		assertThat(filter.isActiveFilter(), equalTo(false));
	}
	
	@Test
	public void shouldReturn3ItemsIfFilterDisabled() throws CrudException {
		filter.disableFilter();
		List<Model> list = dbList.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(3));
	}
	
	@Test
	public void shouldReturn1ItemIfFilterEnabled() throws CrudException {
		filter.active();
		List<Model> list = dbList.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(1));
	}
	
	@Test
	public void shouldSetFilterParameterToInactive() {
		filter.disabled();
		verify(filter).setStatus(Status.Disabled);
		assertThat(filter.isActiveFilter(), equalTo(true));
	}
	
	@Test
	public void shouldSetFilterParameterToActive() {
		filter.active();
		verify(filter).setStatus(Status.Active);
		assertThat(filter.isActiveFilter(), equalTo(true));
	}
	
	@Test
	public void shouldSetFilterParameterToRemoved() {
		filter.removed();
		verify(filter).setStatus(Status.Removed);
		assertThat(filter.isActiveFilter(), equalTo(true));
	}
	

	@Test
	public void shouldDisableFilterIfStatusIsNull() {
		filter.setStatus(null);
		assertThat(filter.isActiveFilter(), equalTo(false));
	}

}