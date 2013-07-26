package org.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DBSave;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionSaveTest {

	ActionSave action;
	@Mock private Persistence persistence;
	private MockSerializationResult result;
	@Mock private EntityManager em;
	private Model model;
	
	@Before
	public void setUp() throws VPlusException {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		Validator validator = new JSR303MockValidator();
		
		DBSave dbSave = spy(new DBSave(em));
		MyEntity entity = new MyEntity(1L, "Test");
		doReturn(entity).when(dbSave).persist(any(MyEntity.class));
		when(persistence.use(DBSave.class)).thenReturn(dbSave);
		action = spy(new ActionSave(new ActionFacade(persistence, result, validator)));
	}
	
	@Test
	public void shouldSaveModel() throws VPlusException {
		Object object = action.withModel(model).operation();
		assertThat(object, notNullValue());
	}

}
