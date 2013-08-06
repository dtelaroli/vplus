package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.vplus.core.controller.Controllers.list;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.controller.CrudImpl.ActionNull;
import org.vplus.core.exception.CrudException;
import org.vplus.core.mock.ActionFacadeMock;
import org.vplus.core.model.Model;
import org.vplus.core.model.MyEntity;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.ioc.Container;

public class CrudImplTest {

	Crud crud;
	Controller controller;
	@Mock Container container;
	private AbstractAction actionList;
	private AbstractAction actionLoad;
	private AbstractAction actionSave;
	private AbstractAction actionDelete;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = spy(new ControllerImpl(container));
		ActionFacadeMock actionFacade = new ActionFacadeMock();
		actionList = spy(new ActionList(actionFacade));
		doReturn(actionList).when(controller).use(type(actionList));

		actionLoad = spy(new ActionLoad(actionFacade));
		doReturn(actionLoad).when(controller).use(type(actionLoad));

		actionSave = spy(new ActionSave(actionFacade));
		doReturn(actionSave).when(controller).use(type(actionSave));

		actionDelete = spy(new ActionDelete(actionFacade));
		doReturn(actionDelete).when(controller).use(type(actionDelete));

		crud = spy(new CrudImpl(controller));
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
	public void shouldReturnResult() throws CrudException {
		doReturn(Arrays.asList(new MyEntity())).when(actionList).operation();
		crud.list();
		assertThat(crud.result().used(), equalTo(true));
	}

	@Test
	public void shouldReturnNullIfActionNotExecutedResult()
			throws CrudException {
		assertThat(crud.result(), nullValue());
	}

	@Test
	public void shouldExecuteList() throws CrudException {
		mockOperation();
		crud.of(MyEntity.class).list();
		verifyExecute(actionList);
		verify(actionList).withStatus(any(Status.class));
		verify(actionList).withLimit(anyInt());
		verify(actionList).withDirection(any(Direction.class));
		verify(actionList).withOrder(anyString());	}

	private void verifyExecute(AbstractAction action) throws CrudException {
		verify(action).of(MyEntity.class);
		verify(action).render();
	}

	@Test
	public void shouldExecuteLoad() throws CrudException {
		doReturn(new MyEntity()).when(actionLoad).operation();
		crud.of(MyEntity.class).load(new MyEntity());
		verify(actionLoad).withModel(any(Model.class));
		verifyExecute(actionLoad);
	}

	@Test
	public void shouldExecuteSave() throws CrudException {
		doReturn(new MyEntity()).when(actionSave).operation();
		crud.of(MyEntity.class).save(new MyEntity());
		verify(actionSave).withModel(any(Model.class));
		verifyExecute(actionSave);
	}

	@Test
	public void shouldExecuteDelete() throws CrudException {
		doReturn(new MyEntity()).when(actionDelete).operation();
		crud.of(MyEntity.class).delete(new MyEntity());
		verify(actionDelete).withModel(any(Model.class));
		verifyExecute(actionDelete);
	}
	
	@Test
	public void shouldExecuteActionNull() throws CrudException {
		ActionNull actionNull = new CrudImpl.ActionNull(null);
		assertThat(actionNull.operation(), nullValue());
		assertThat(actionNull.withOrder(null), nullValue());
		assertThat(actionNull.withDirection(null), nullValue());
		assertThat(actionNull.result(), nullValue());
	}
	
	@Test
	public void shouldSetAscOrder() throws CrudException {
		mockOperation();
		crud.asc();
		assertThat(crud.isAsc(), equalTo(true));
	}

	private void mockOperation() throws CrudException {
		doReturn(Arrays.asList(new MyEntity())).when(actionList).operation();
	}
	
	@Test
	public void shouldSetDescOrder() throws CrudException {
		mockOperation();
		crud.desc();
		assertThat(crud.isAsc(), equalTo(false));
	}
	
	@Test
	public void shouldSetDescDirection() throws CrudException {
		mockOperation();
		crud.withDirection(Direction.DESC);
		assertThat(crud.isAsc(), equalTo(false));
	}
	
	@Test
	public void shouldSetOrder() throws CrudException {
		mockOperation();
		crud.withOrder("order");
		assertThat(crud.order(), equalTo("order"));
	}
	
	@Test
	public void shouldSetLimit() throws CrudException {
		mockOperation();
		crud.withLimit(1);
		assertThat(crud.limit(), equalTo(1));
	}
	
	@Test
	public void shouldSetStatus() throws CrudException {
		mockOperation();
		crud.withStatus(Status.Active);
		assertThat(crud.status(), equalTo(Status.Active));
	}
	
	@Test
	public void shouldExecuteUseMethod() throws CrudException {
		mockOperation();
		Class<ActionList> list = list();
		crud.use(list);
		verify(controller).use(list);
	}
	
}