package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.vplus.core.persistence.Persistences.list;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.generics.StatusFilter;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.persistence.PersistenceImpl;
import org.vplus.core.util.TestUtil;
import org.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Container;

public class ActionListFilterTest {

	ActionList action;
	TestUtil test;
	@Mock Container container;
	Persistence persistence;
	@Mock Result result;
	Validator validator;
	StatusFilter filter;
	private ActionList actionList;
	@Mock TypeUtil typeUtil;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		persistence = new PersistenceImpl(container);
		filter = new StatusFilter(test.entityManager());
		actionList = new ActionList(new ActionFacadeImpl(persistence, result, validator, typeUtil));
		doReturn(new DBList(test.entityManager())).when(container).instanceFor(list());
	}

	@After
	public void tearDown() throws Exception {
		test.cleanAndDestroy();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturn1ItemWithFilterEnabled() throws CrudException {
		filter.active();
		List<Model> operation = (List<Model>) actionList.of(MyEntity.class).operation();
		assertThat(operation.size(), equalTo(1));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturn3ItemsWithFilterDisabled() throws CrudException {
		filter.disableFilter();
		List<Model> operation = (List<Model>) actionList.of(MyEntity.class).operation();
		assertThat(operation.size(), equalTo(3));
	}

}