package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.controller.Order;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.util.TestUtil;

public class DBListTest {

	DBList listDAO;
	private TestUtil test;
	
	@Before
	public void setUp() throws Exception {
		test = TestUtil.create();
		test.from(MyEntity.class).init();
		listDAO = new DBList(test.entityManager());
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	@Test
	public void shouldReturn3ItemsFromDBUnit() throws CrudException {
		List<Model> list = listDAO.of(MyEntity.class).find();
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).getId(), equalTo(1L));
	}
	
	@Test
	public void shouldSetOrder() {
		String order = "order";
		listDAO = listDAO.withOrder(order);
		assertThat(listDAO.order(), equalTo(order));
	}
	
	@Test
	public void shouldReturn3ItemsOrderedAsc() throws CrudException {
		List<Model> list = listDAO.of(MyEntity.class).withOrder("name").withDirection(Order.ASC).find();
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).getId(), equalTo(2L));
		list = listDAO.of(MyEntity.class).withOrder("name").find();
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).getId(), equalTo(2L));
	}
	
	@Test
	public void shouldReturn3ItemsOrderedDesc() throws CrudException {
		List<Model> list = listDAO.of(MyEntity.class).withOrder("name").withDirection(Order.DESC).find();
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).getId(), equalTo(3L));
		list = listDAO.of(MyEntity.class).withOrder("name").find();
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).getId(), equalTo(3L));
	}
	
	@Test
	public void shouldSetDefaultLimitIfLimitIsNull() {
		listDAO.withLimit(null);
		assertThat(listDAO.limit(), equalTo(DBList.DEFAULT_LIMIT));
	}
	
	@Test
	public void shouldSetLimit() {
		listDAO.withLimit(1);
		assertThat(listDAO.limit(), equalTo(1));
	}
}