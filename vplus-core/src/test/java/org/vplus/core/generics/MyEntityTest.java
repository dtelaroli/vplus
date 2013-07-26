package org.vplus.core.generics;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MyEntityTest {

	MyEntity my;
	
	@Before
	public void setUp() throws Exception {
		my = new MyEntity();
	}

	@Test
	public void shouldRetornNameOnLabel() {
		my.name = "Teste";
		assertThat(my.getLabel(), equalTo("Teste"));
	}
	
	@Test
	public void shouldCompareTwoEntity() {
		my.setId(1L);
		MyEntity other = new MyEntity();
		other.setId(1L);
		assertThat(my.equals(other), is(true));
		assertThat(my, equalTo(other));
		assertThat(my, equalTo(my));
		assertThat(my, not(equalTo(null)));
	}
	
	@Test
	public void shouldCompareTwoEntityHashCode() {
		my.setId(1L);
		MyEntity other = new MyEntity(1L);
		assertThat(my.hashCode(), equalTo(other.hashCode()));
	}
	
	@Test
	public void shouldReturnId() {
		my.setId(1L);
		assertThat(my.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnCreated() {
		assertThat(my.getCreated(), notNullValue());
	}
	
	@Test
	public void shouldReturnModified() {
		assertThat(my.getModified(), notNullValue());
	}
	
	@Test
	public void shouldReturnActive() {
		assertThat(my.getStatus(), equalTo(Status.ACTIVE));
	}
	
	@Test
	public void shouldSetInactive() {
		my.setStatus(Status.INACTIVE);
		assertThat(my.getStatus(), equalTo(Status.INACTIVE));
	}
	
	@Test
	public void shouldSetRemoved() {
		my.setStatus(Status.REMOVED);
		assertThat(my.getStatus(), equalTo(Status.REMOVED));
	}
	
	@Test
	public void shouldReturnIncludes() {
		assertThat(my.getIncludes().length, equalTo(2));
	}

}