package org.vplus.core.persistence;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.vplus.core.persistence.DBDelete;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.DBLoad;
import org.vplus.core.persistence.DBSave;
import org.vplus.core.persistence.Persistences;

public class PersistencesTest {
	
	@Before
	public void setUp() {
	}
	
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