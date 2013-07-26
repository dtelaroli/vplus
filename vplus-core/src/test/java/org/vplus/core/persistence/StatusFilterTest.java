package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.generics.Status;
import org.vplus.core.util.TestUtil;

public class StatusFilterTest {

	StatusFilter filter;
	private TestUtil test;
	private DBList dbList;
	
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
		filter.enableFilter();
		assertThat(filter.filter(), notNullValue());
	}
	
	@Test
	public void shouldDisableFilter() {
		filter.disableFilter();
		assertThat(filter.filter(), nullValue());
	}
	
	@Test
	public void shouldReturnTrueIfFilterEnabled() {
		filter.enableFilter();
		assertThat(filter.isActive(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfFilterEnabled() {
		filter.disableFilter();
		assertThat(filter.isActive(), is(false));
	}
	
	@Test
	public void shouldReturn3ItemsIfFilterDisabled() throws VPlusException {
		filter.disableFilter();
		List<Model> list = dbList.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(3));
	}
	
	@Test
	public void shouldReturn1ItemIfFilterEnabled() throws VPlusException {
		filter.enableFilter();
		List<Model> list = dbList.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(1));
	}
	
	@Test
	public void shouldSetFilterParameterToInactive() {
		filter.setInactive();
		verify(filter).setParameter(Status.INACTIVE);
		assertThat(filter.isActive(), is(true));
	}
	
	@Test
	public void shouldSetFilterParameterToActive() {
		filter.setActive();
		verify(filter).setParameter(Status.ACTIVE);
		assertThat(filter.isActive(), is(true));
	}
	
	@Test
	public void shouldSetFilterParameterToRemoved() {
		filter.setRemoved();
		verify(filter).setParameter(Status.REMOVED);
		assertThat(filter.isActive(), is(true));
	}

}