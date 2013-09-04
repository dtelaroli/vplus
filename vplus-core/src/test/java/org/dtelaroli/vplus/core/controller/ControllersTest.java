package org.dtelaroli.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ControllersTest {
	
	@Test
	public void shouldReturnActionDelete() {
		assertThat(Controllers.delete().isAssignableFrom(ActionDelete.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnActionList() {
		assertThat(Controllers.list().isAssignableFrom(ActionList.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnActionLoad() {
		assertThat(Controllers.load().isAssignableFrom(ActionLoad.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnActionSave() {
		assertThat(Controllers.save().isAssignableFrom(ActionSave.class), equalTo(true));
	}

}