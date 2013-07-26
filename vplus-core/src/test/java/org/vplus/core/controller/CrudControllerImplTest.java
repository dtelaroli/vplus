package org.vplus.core.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.mock.ActionFacadeMock;

import br.com.caelum.vraptor.ioc.Container;

public class CrudControllerImplTest {

	CrudController crud;
	Controller controller;
	@Mock
	Container container;
	private AbstractAction actionList;
	private AbstractAction actionLoad;
	private AbstractAction actionSave;
	private AbstractAction actionDelete;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = spy(new ControllerImpl(container));
		actionList = spy(new ActionList(new ActionFacadeMock()));
		doReturn(actionList).when(controller).use(type(actionList));

		actionLoad = spy(new ActionLoad(new ActionFacadeMock()));
		doReturn(actionLoad).when(controller).use(type(actionLoad));

		actionSave = spy(new ActionSave(new ActionFacadeMock()));
		doReturn(actionSave).when(controller).use(type(actionSave));

		actionDelete = spy(new ActionDelete(new ActionFacadeMock()));
		doReturn(actionDelete).when(controller).use(type(actionDelete));

		crud = spy(new CrudControllerImpl(controller));
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Action> type(Action action) {
		return (Class<? extends Action>) action.getClass().getSuperclass();
	}

	@Test
	public void shouldSetModel() {
		assertThat(crud.type(), nullValue());
		crud = crud.of(MyEntity.class);
		assertThat(crud.type(), notNullValue());
	}

	@Test
	public void shouldReturnResult() throws VPlusException {
		doNothing().when(crud).actionExecute();
		crud.list();
		assertThat(crud.result(), notNullValue());
	}

	@Test
	public void shouldReturnNullIfActionNotExecutedResult()
			throws VPlusException {
		assertThat(crud.result(), nullValue());
	}

	@Test
	public void shouldExecuteList() throws VPlusException {
		doReturn(Arrays.asList(new MyEntity())).when(actionList).operation();
		crud.of(MyEntity.class).list();
		verifyExecute(actionList);
	}

	private void verifyExecute(AbstractAction action) throws VPlusException {
		verify(action).of(MyEntity.class);
		verify(action).render();
	}

	@Test
	public void shouldExecuteLoad() throws VPlusException {
		doReturn(new MyEntity()).when(actionLoad).operation();
		crud.of(MyEntity.class).load(new MyEntity());
		verify(actionLoad).withModel(any(Model.class));
		verifyExecute(actionLoad);
	}

	@Test
	public void shouldExecuteSave() throws VPlusException {
		doReturn(new MyEntity()).when(actionSave).operation();
		crud.of(MyEntity.class).save(new MyEntity());
		verify(actionSave).withModel(any(Model.class));
		verifyExecute(actionSave);
	}

	@Test
	public void shouldExecuteDelete() throws VPlusException {
		doReturn(new MyEntity()).when(actionDelete).operation();
		crud.of(MyEntity.class).delete(new MyEntity());
		verify(actionDelete).withModel(any(Model.class));
		verifyExecute(actionDelete);
	}

}
