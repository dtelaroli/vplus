package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
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
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.persistence.DBLoad;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionLoadTest {

	ActionLoad action;
	@Mock private Persistence persistence;
	private MockSerializationResult result;
	@Mock private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		
		DBLoad dbLoad = spy(new DBLoad(em));
		doReturn(new MyEntity()).when(dbLoad).find(any(MyEntity.class));
		when(persistence.use(DBLoad.class)).thenReturn(dbLoad);
		action = spy(new ActionLoad(persistence, result));
	}

	@Test
	public void shouldReturnEntity() throws VPlusException {
		Object object = action.of(MyEntity.class).persistence();
		assertThat(object, notNullValue());
	}
	
	@Test
	public void shouldSetModel() {
		action = action.withId(1L);
		assertThat(action.model().getId(), equalTo(1L));
	}
	
}