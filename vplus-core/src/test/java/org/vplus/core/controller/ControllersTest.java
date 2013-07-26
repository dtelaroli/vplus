package org.vplus.core.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ControllersTest {
	
	@Test
	public void shouldReturnActionDelete() {
		assertThat(Controllers.delete().isAssignableFrom(ActionDelete.class), is(true));
	}
	
	@Test
	public void shouldReturnActionList() {
		assertThat(Controllers.list().isAssignableFrom(ActionList.class), is(true));
	}
	
	@Test
	public void shouldReturnActionLoad() {
		assertThat(Controllers.load().isAssignableFrom(ActionLoad.class), is(true));
	}
	
	@Test
	public void shouldReturnActionSave() {
		assertThat(Controllers.save().isAssignableFrom(ActionSave.class), is(true));
	}

}