package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.persistence.Direction;

public class ScaffoldTest {

	Scaffold<MyEntity> scaffold;
	@Mock CrudController crud;
	
	static class MyScaffold extends Scaffold<MyEntity>{
		public MyScaffold(CrudController controller) {
			super(controller);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(crud.of(MyEntity.class)).thenReturn(crud);
		when(crud.withOrder(anyString())).thenReturn(crud);
		when(crud.withLimit(anyInt())).thenReturn(crud);
		when(crud.withDirection(any(Direction.class))).thenReturn(crud);
		scaffold = new MyScaffold(crud);
	}

	@Test
	public void shouldGetTypeFromGeneric() {
		Scaffold<Model> sc = new Scaffold<Model>(crud) {};
		assertThat(sc.getType().isAssignableFrom(Model.class), equalTo(true));
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
	
}