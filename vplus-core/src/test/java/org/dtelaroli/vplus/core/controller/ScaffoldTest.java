package org.dtelaroli.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.mock.CrudMock;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.persistence.Direction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ScaffoldTest {

	Scaffold<MyEntity> scaffold;
	@Mock Crud crud;
	
	static class MyScaffold extends Scaffold<MyEntity>{
		public MyScaffold(Crud crud) {
			super(crud);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		crud = spy(new CrudMock());
		scaffold = new MyScaffold(crud);
	}

	@Test
	public void shouldGetTypeFromGeneric() {
		Scaffold<Model> sc = new Scaffold<Model>(crud) {};
		assertThat(sc.type().isAssignableFrom(Model.class), equalTo(true));
	}
	
	@Test
	public void shouldInvokeListMethod() throws CrudException {
		scaffold.all();
		verify(crud).list();
	}
	
	@Test
	public void shouldInvokeListOrderMethod() throws CrudException {
		scaffold.all("order", Direction.ASC, 1);
		verify(crud).withOrder("order");
		verify(crud).withDirection(Direction.ASC);
		verify(crud).withLimit(1);
	}
	
	@Test
	public void shouldInvokeGetMethod() throws CrudException {
		MyEntity model = new MyEntity();
		scaffold.get(model);
		verify(crud).load(model);
	}
	
	@Test
	public void shouldInvokeSaveInAddMethod() throws CrudException {
		MyEntity model = new MyEntity();
		scaffold.add(model);
		verify(crud).save(model);
	}
	
	@Test
	public void shouldInvokeSaveInEditMethod() throws CrudException {
		MyEntity model = new MyEntity();
		scaffold.edit(model);
		verify(crud).save(model);
	}

	@Test
	public void shouldInvokeDeleteMethod() throws CrudException {
		MyEntity model = new MyEntity();
		scaffold.remove(model);
		verify(crud).delete(model);
	}
	
	@Test
	public void shouldInvokeListWithFilter() throws CrudException {
		scaffold.all(Status.Disabled);
		verify(crud).list();
		verify(crud).withStatus(Status.Disabled);
	}
	
	@Test
	public void shouldExecuteUseMethod() throws CrudException {
		assertThat(scaffold.crud(), notNullValue());
	}
}