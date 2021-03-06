package org.dtelaroli.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import javax.persistence.EntityManager;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.mock.ActionFacadeMock;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.persistence.DBSave;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.validator.ValidationException;

public class ActionSaveTest {

	ActionSave action;
	@Mock private EntityManager em;
	private Model model;
	
	@Before
	public void setUp() throws CrudException {
		MockitoAnnotations.initMocks(this);
		
		DBSave dbSave = spy(new DBSave(em));
		MyEntity entity = new MyEntity(1L, "Test");
		doReturn(entity).when(dbSave).persist(any(MyEntity.class));
		action = spy(new ActionSave(new ActionFacadeMock().withPersistence(dbSave)));
	}
	
	@Test
	public void shouldSaveModel() throws CrudException {
		Object object = action.withModel(model).operation();
		assertThat(object, notNullValue());
	}
	
	@Test(expected = ValidationException.class)
	public void shouldDispatchValidationExceptionIfInvalid() throws CrudException {
		action.withModel(new MyEntity()).operation();
	}

}
