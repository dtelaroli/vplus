package org.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DBDelete;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionDeleteTest {

	ActionDelete action;
	@Mock private Persistence persistence;
	private MockSerializationResult result;
	@Mock private EntityManager em;
	private Model model;
	
	@Before
	public void setUp() throws VPlusException {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		
		DBDelete db = spy(new DBDelete(em));
		doNothing().when(db).delete(any(MyEntity.class));
		when(persistence.use(DBDelete.class)).thenReturn(db);
		action = spy(new ActionDelete(new ActionFacade(persistence, result, null)));
	}
	
	@Test
	public void shouldDeleteModel() throws VPlusException {
		Object object = action.withModel(model).operation();
		assertThat(object, notNullValue());
	}

}
