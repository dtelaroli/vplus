package org.vplus.core.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.CrudException;
import org.vplus.core.model.MyEntity;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.Direction;
import org.vplus.core.util.TestUtil;

import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.validator.ValidationException;

public class ActionListTest {

	ActionList controller;
	private MockSerializationResult result;
	private TestUtil test;
	@Mock private EntityManager em;
	private DBList dblist;
	private ActionFacade facade;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		dblist = spy(new DBList(em));
		
		test = TestUtil.create();
		result = test.resultMock();
		
		doReturn(Arrays.asList(new MyEntity())).when(dblist).find();
		
		facade = spy(test.actionFacadeMock().withPersistence(dblist));
		controller = spy(new ActionList(facade));
	}

	@Test
	public void shouldSetType() {
		controller = (ActionList) controller.of(MyEntity.class);
		assertThat(controller.type().isAssignableFrom(MyEntity.class), equalTo(true));
	}
	
	@Test
	public void shouldExecuteOperation() throws CrudException {
		controller.operation();
		verify(dblist).withLimit(anyInt());
		verify(dblist).withDirection(any(Direction.class));
		verify(dblist).withOrder(anyString());
	}
	
	@Test
	public void shouldRenderJson() throws Exception {
		doReturn(Arrays.asList(new MyEntity(1L, "Teste"))).when(controller).operation();
		controller.withModel(new MyEntity()).render();
		assertThat(result.serializedResult(), containsString("[{\"id\": 1"));
	}
	
	@Test
	public void shouldReturnList() throws CrudException {
		Object object = controller.of(MyEntity.class).operation();
		assertThat(object, notNullValue());
	}
	
	@Test(expected = ValidationException.class)
	public void shouldReturnErrorIfNotExists() throws Exception {
		doReturn(null).when(controller).operation();
		controller.render();
	}
	
	@Test
	public void shouldReturn2ItemsFromGetIncludes() {
		String[] includes = controller.getModel(new MyEntity()).includes();
		assertThat(includes.length, equalTo(2));
	}
	
	@Test
	public void shouldReturn0ItemsIfOtherType() {
		String[] includes = controller.getModel("String").includes();
		assertThat(includes.length, equalTo(0));
	}
	
	@Test
	public void shouldReturn2ItemsIfListOfModel() {
		String[] includes = controller.getModel(Arrays.asList(new MyEntity())).includes();
		assertThat(includes.length, equalTo(2));
	}
	
	@Test
	public void shouldReturn0ItemsIfListOther() {
		String[] includes = controller.getModel(Arrays.asList("String")).includes();
		assertThat(includes.length, equalTo(0));
	}
	
	@Test
	public void shouldSetOrder() throws CrudException {
		String string = "order";
		controller = (ActionList) controller.withOrder(string);
		controller.render();
		verify(dblist).withOrder(anyString());
	}
	
	@Test
	public void shouldSetLimit() throws CrudException {
		controller.withLimit(1);
		assertThat(controller.limit(), equalTo(1));
	}
	
	@Test
	public void shouldSetStatusInFilter() {
		controller.withStatus(Status.Active);
		verify(facade).setFilter(any(Status.class));
	}
	
}