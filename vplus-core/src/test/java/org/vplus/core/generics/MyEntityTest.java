package org.vplus.core.generics;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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
	public void shouldCompareTwoEntity() {
		my = new MyEntity(1L);
		MyEntity other = new MyEntity(1L);
		assertThat(my.equals(other), equalTo(true));
		assertThat(my, equalTo(other));
		assertThat(my, equalTo(my));
		assertThat(my, not(equalTo(null)));
	}
	
	@Test
	public void shouldCompareTwoEntityHashCode() {
		my = new MyEntity(1L);
		MyEntity other = new MyEntity(1L);
		assertThat(my.hashCode(), equalTo(other.hashCode()));
	}
	
	@Test
	public void shouldReturnId() {
		my = new MyEntity(1L);
		assertThat(my.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldReturnCreated() {
		my.beforeInsert();
		assertThat(my.createdAt(), notNullValue());
	}
	
	@Test
	public void shouldReturnModified() {
		my.beforeUpdate();
		assertThat(my.modifiedAt(), notNullValue());
	}
	
	@Test
	public void shouldReturnActive() {
		assertThat(my.status(), equalTo(Status.ACTIVE));
	}
	
	@Test
	public void shouldSetInactive() {
		my.withStatus(Status.INACTIVE);
		assertThat(my.status(), equalTo(Status.INACTIVE));
	}
	
	@Test
	public void shouldSetRemoved() {
		my.withStatus(Status.REMOVED);
		assertThat(my.status(), equalTo(Status.REMOVED));
	}
	
	@Test
	public void shouldReturnIncludes() {
		assertThat(my.includes().length, equalTo(2));
	}
	
	@Test
	public void shouldReturnExcludes() {
		assertThat(my.excludes().length, equalTo(1));
	}
	
	@Test
	public void shouldReturnToString() {
		my = new MyEntity(1L, "test");
		assertThat(my.toString(), containsString("ACTIVE"));
		assertThat(my.toString(), containsString("1"));
	}
	
	@Test
	public void shouldReturnOrderField() {
		assertThat(my.orderField(), equalTo("id"));
	}
	
	@Test
	public void shouldReturnIdValueByLabelAndName() throws Exception {
		my = new MyEntity(1L, "test");
		assertThat(my.getLabel(), equalTo("1"));
		assertThat(my.name(), equalTo("test"));
	}
	
}