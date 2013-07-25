package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.vplus.core.persistence.MyEntity;

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

}