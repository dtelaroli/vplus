package org.dtelaroli.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PersistencesTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void shouldReturnList() {
		assertThat(Persistences.list().isAssignableFrom(DBList.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnLoad() {
		assertThat(Persistences.load().isAssignableFrom(DBLoad.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnSave() {
		assertThat(Persistences.save().isAssignableFrom(DBSave.class), equalTo(true));
	}
	
	@Test
	public void shouldReturnDelete() {
		assertThat(Persistences.delete().isAssignableFrom(DBDelete.class), equalTo(true));
	}

}