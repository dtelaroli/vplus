package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.vplus.core.persistence.Persistences.list;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.MyEntity;

import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.serialization.JSONSerialization;

public class ControllerImplTest {

	Controller controller;
	@Mock Container container;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Database database = null;
		View view = null;
		controller = new ControllerImpl(database, view);
	}
	
	interface MyController extends Action {
	}
	class MyControl implements MyController {
	}

	@Test
	public void shouldSetAndReturnType() {
		when(container.instanceFor(MyController.class)).thenReturn(new MyControl());
		
		Controller c = controller.of(MyEntity.class);
		assertThat(c.type().isAssignableFrom(MyEntity.class), is(true));
	}
	
	@Test
	public void shouldSetAndReturnPersistenceOperation() {
		Controller c = controller.withPersistence(list());
		assertThat(c.persistence().isAssignableFrom(DBList.class), is(true));
	}
	
	@Test
	public void shouldSetAndReturnViewRender() {
		Controller c = controller.withResult(json());
		assertThat(c.result().isAssignableFrom(JSONSerialization.class), is(true));
	}

}
