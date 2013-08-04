package org.vplus.core.generics;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StatusTest {

	@Test
	public void shouldReturnIfInInactive() {
		assertThat(Status.Inactive.isInactive(), equalTo(true));
		assertThat(Status.Active.isInactive(), equalTo(false));
		assertThat(Status.Removed.isInactive(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInActive() {
		assertThat(Status.Inactive.isActive(), equalTo(false));
		assertThat(Status.Active.isActive(), equalTo(true));
		assertThat(Status.Removed.isActive(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInRemoved() {
		assertThat(Status.Inactive.isRemoved(), equalTo(false));
		assertThat(Status.Active.isRemoved(), equalTo(false));
		assertThat(Status.Removed.isRemoved(), equalTo(true));
	}
	

}