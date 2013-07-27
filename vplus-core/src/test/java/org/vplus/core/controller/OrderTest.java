package org.vplus.core.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.vplus.core.exception.CrudException;

public class OrderTest {

	@Test
	public void shouldReturnTrueIfAsc() {
		assertTrue(Order.ASC.isAsc());
	}
	
	@Test
	public void shouldReturnFalseIfDesc() {
		assertFalse(Order.DESC.isAsc());
	}
	
	@Test
	public void shouldReturnFalseIfNull() {
		assertTrue(Order.NULL.isAsc());
	}
	
	@Test(expected = CrudException.class)
	public void shouldDispatchErrorIfDirectionNull() throws CrudException {
		Order.NULL.makeOrder(null, null, "teste");
	}
	
	@Test(expected = CrudException.class)
	public void shouldDispatchErrorIfOrderNull() throws CrudException {
		Order.ASC.makeOrder(null, null, null);
	}

}
