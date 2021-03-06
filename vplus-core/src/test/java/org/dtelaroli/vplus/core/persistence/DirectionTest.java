package org.dtelaroli.vplus.core.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.junit.Test;

public class DirectionTest {

	@Test
	public void shouldReturnTrueIfAsc() {
		assertTrue(Direction.ASC.isAsc());
	}
	
	@Test
	public void shouldReturnFalseIfDesc() {
		assertFalse(Direction.DESC.isAsc());
	}
	
	@Test
	public void shouldReturnFalseIfNull() {
		assertTrue(Direction.NULL.isAsc());
	}
	
	@Test(expected = CrudException.class)
	public void shouldDispatchErrorIfDirectionNull() throws CrudException {
		Direction.NULL.makeOrder(null, null, "teste");
	}
	
	@Test(expected = CrudException.class)
	public void shouldDispatchErrorIfOrderNull() throws CrudException {
		Direction.ASC.makeOrder(null, null, null);
	}

}
