package org.dtelaroli.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import javax.persistence.EntityManager;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.mock.ActionFacadeMock;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.persistence.DBLoad;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ActionLoadTest {

	ActionLoad action;
	@Mock private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		DBLoad dbLoad = spy(new DBLoad(em));
		doReturn(new MyEntity()).when(dbLoad).find(any(MyEntity.class));
		action = spy(new ActionLoad(new ActionFacadeMock().withPersistence(dbLoad)));
	}

	@Test
	public void shouldReturnEntity() throws CrudException {
		Object object = action.of(MyEntity.class).operation();
		assertThat(object, notNullValue());
	}
	
}