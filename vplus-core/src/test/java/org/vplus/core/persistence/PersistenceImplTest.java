package org.vplus.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.vplus.core.persistence.Persistences.delete;
import static org.vplus.core.persistence.Persistences.list;
import static org.vplus.core.persistence.Persistences.load;
import static org.vplus.core.persistence.Persistences.save;

import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.util.TestUtil;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class PersistenceImplTest {
	
	Persistence persistence;
	@Mock Container container;
	private TestUtil test;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		persistence = new PersistenceImpl(container);
		test = TestUtil.create();
		test.from(MyEntity.class).init();
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		test.cleanAndDestroy();
	}
	
	class DaoImpl implements Dao {
	}

	@Test
	public void shouldReturnInstanceOfDao() {
		when(container.instanceFor(DaoImpl.class)).thenReturn(new DaoImpl());
		assertThat(persistence.use(DaoImpl.class), instanceOf(DaoImpl.class));
	}
	
	@Test
	public void shouldReturnInstanceOfList() {
		when(container.instanceFor(DBList.class)).thenReturn(new DBList(null));
		assertThat(persistence.use(list()), instanceOf(DBList.class));
	}
	
	@Test
	public void shouldReturnListWith3Items() throws CrudException {
		when(container.instanceFor(DBList.class)).thenReturn(new DBList(test.entityManager()));
		List<Model> find = persistence.use(list()).of(MyEntity.class).find();
		assertThat(find.size(), equalTo(3));
	}
	
	@Test
	public void shouldReturnListWithItem1() throws Exception {
		when(container.instanceFor(DBLoad.class)).thenReturn(new DBLoad(test.entityManager()));
		Model model = persistence.use(load()).of(MyEntity.class).find(new MyEntity(1L));
		assertThat(model.getId(), equalTo(1L));
		assertThat(model.getLabel(), equalTo("João"));
	}
	
	@Test
	public void shouldSaveItem() {
		test.beginTransaction();
		when(container.instanceFor(DBSave.class)).thenReturn(new DBSave(test.entityManager()));
		Model model = new MyEntity("sdf");
		
		model = persistence.use(save()).persist(model);
		test.commit();
		
		assertThat(model, notNullValue());
	}
	
	@Test
	public void shouldDeleteItem() {
		test.beginTransaction();
		when(container.instanceFor(DBDelete.class)).thenReturn(new DBDelete(test.entityManager()));
		MyEntity model = new MyEntity(1L);
		
		persistence.use(delete()).of(MyEntity.class).delete(model);
		test.commit();
	}

}