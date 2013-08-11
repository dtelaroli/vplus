package org.vplus.core.model;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StatusTest {

	@Test
	public void shouldReturnIfInInactive() {
		assertThat(Status.Disabled.isDisabled(), equalTo(true));
		assertThat(Status.Active.isDisabled(), equalTo(false));
		assertThat(Status.Removed.isDisabled(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInActive() {
		assertThat(Status.Disabled.isActive(), equalTo(false));
		assertThat(Status.Active.isActive(), equalTo(true));
		assertThat(Status.Removed.isActive(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInRemoved() {
		assertThat(Status.Disabled.isRemoved(), equalTo(false));
		assertThat(Status.Active.isRemoved(), equalTo(false));
		assertThat(Status.Removed.isRemoved(), equalTo(true));
	}
	

}