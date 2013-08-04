package org.vplus.core.generics;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StatusTest {

	@Test
	public void shouldReturnIfInInactive() {
		assertThat(Status.INACTIVE.isInactive(), equalTo(true));
		assertThat(Status.ACTIVE.isInactive(), equalTo(false));
		assertThat(Status.REMOVED.isInactive(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInActive() {
		assertThat(Status.INACTIVE.isActive(), equalTo(false));
		assertThat(Status.ACTIVE.isActive(), equalTo(true));
		assertThat(Status.REMOVED.isActive(), equalTo(false));
	}
	
	@Test
	public void shouldReturnIfInRemoved() {
		assertThat(Status.INACTIVE.isRemoved(), equalTo(false));
		assertThat(Status.ACTIVE.isRemoved(), equalTo(false));
		assertThat(Status.REMOVED.isRemoved(), equalTo(true));
	}
	

}