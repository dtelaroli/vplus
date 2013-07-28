package org.vplus.core.util;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.ModelPlus;
import org.vplus.core.generics.MyEntity;

public class TypeUtilTest {

	TypeUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = new TypeUtil();
	}

	@Test
	public void shouldReturnTrueIfIsMyEntity() {
		assertThat(util.of(Model.class).compare(MyEntity.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnTrueIfIsModelPlus() {
		assertThat(util.of(Model.class).compare(ModelPlus.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnFalseIfIsObject() {
		assertThat(util.of(Model.class).compare(Object.class), equalTo(false));
	}
	
	@Test
	public void shouldReturnTrueIfIsList() {
		assertThat(util.of(List.class).compare(ArrayList.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnTrueIfIsInstanceList() {
		assertThat(util.of(Model.class).compare(Arrays.asList(new MyEntity())), equalTo(true));
	}
	
	@Test
	public void shouldReturnTrueIfIsAssignFrom() {
		assertThat(util.of(Model.class).compare(ModelPlus.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnTrueIfIsListInstanceOfObject() {
		assertThat(util.of(Model.class).isListFrom(Arrays.asList(new MyEntity())), equalTo(true));
	}
	
	@Test
	public void shouldReturnFalseIfIsListEmpty() {
		assertThat(util.of(Model.class).isListFrom(new ArrayList<MyEntity>()), equalTo(false));
	}
}
