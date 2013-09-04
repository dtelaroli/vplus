package org.dtelaroli.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.mock.ActionFacadeMock;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.persistence.DBDelete;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ActionDeleteTest {

	ActionDelete action;
	@Mock private EntityManager em;
	private Model model;
	
	@Before
	public void setUp() throws CrudException, IOException {
		MockitoAnnotations.initMocks(this);
		
		DBDelete dbDelete = spy(new DBDelete(em));
		doNothing().when(dbDelete).delete(any(Model.class));
		ActionFacade facade = new ActionFacadeMock().withPersistence(dbDelete);
		action = new ActionDelete(facade);
		model = new MyEntity(1L);
	}
	
	@Test
	public void shouldDeleteModel() throws CrudException {
		Object object = action.withModel(model).operation();
		assertThat(object, notNullValue());
	}

}
