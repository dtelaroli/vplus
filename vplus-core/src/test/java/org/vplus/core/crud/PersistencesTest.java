package org.vplus.core.crud;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PersistencesTest {
	
	@Test
	public void shouldReturnList() {
		assertThat(Persistences.list().isAssignableFrom(DBList.class), is(true));
	}
	
	@Test
	public void shouldReturnLoad() {
		assertThat(Persistences.load().isAssignableFrom(DBLoad.class), is(true));
	}
	
	@Test
	public void shouldReturnSave() {
		assertThat(Persistences.save().isAssignableFrom(DBSave.class), is(true));
	}
	
	@Test
	public void shouldReturnDelete() {
		assertThat(Persistences.delete().isAssignableFrom(DBDelete.class), is(true));
	}

}