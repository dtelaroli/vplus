package org.vplus.core.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionListTest {

	ActionList controller;
	@Mock private Persistence persistence;
	private MockSerializationResult result;
	@Mock private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		DBList dblist = spy(new DBList(null));
		doReturn(Arrays.asList(new MyEntity())).when(dblist).find();
		when(persistence.use(DBList.class)).thenReturn(dblist);
		controller = spy(new ActionList(persistence, result));
	}

	@Test
	public void shouldSetType() {
		controller = (ActionList) controller.of(MyEntity.class);
		assertThat(controller.type().isAssignableFrom(MyEntity.class), is(true));
	}
	
	@Test
	public void shouldRenderJson() throws Exception {
		doReturn(Arrays.asList(new MyEntity(1L, "Teste"))).when(controller).persistence();
		controller.render();
		assertThat(result.serializedResult(), containsString("<id>1</id>"));
	}
	
	@Test
	public void shouldReturnList() throws VPlusException {
		Object object = controller.of(MyEntity.class).persistence();
		assertThat(object, notNullValue());
	}
}
