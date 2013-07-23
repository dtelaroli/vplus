package org.vplus.core.crud;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.dbunit.DBUnitUtil;
import org.vplus.core.jpa.JPAUtil;

import br.com.caelum.vraptor.environment.DefaultEnvironment;

public class DefaultListTest {

	DefaultList listDAO;
	private DBUnitUtil util;
	
	@Before
	public void setUp() throws Exception {
		JPAUtil jpa = new JPAUtil().withUnit("test");
		util = new DBUnitUtil(jpa, new DefaultEnvironment("vplus"));
		util.from(MyEntity.class).init();
		listDAO = new DefaultList(jpa.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		util.cleanAndDestroy();
	}
	
	@Test
	public void shouldSetClassType() {
		listDAO = listDAO.of(MyEntity.class);
		assertThat(listDAO.type().isAssignableFrom(MyEntity.class), is(true));
	}

	@Test
	public void shouldReturn3Items() {
		List<MyEntity> list = listDAO.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDispatchErrorIfTypeNotConfig() {
		listDAO.find();
	}

}
