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
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DAO;
import org.vplus.core.persistence.DBDelete;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.DBLoad;
import org.vplus.core.persistence.DBSave;
import org.vplus.core.persistence.DefaultDBDelete;
import org.vplus.core.persistence.DefaultDBList;
import org.vplus.core.persistence.DefaultDBLoad;
import org.vplus.core.persistence.DefaultDBSave;
import org.vplus.core.persistence.DefaultPersistence;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TestUtil;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class DefaultPersistenceTest {
	
	Persistence persistence;
	@Mock Container container;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		persistence = new DefaultPersistence(container);
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
	}
	
	class DaoImpl implements DAO {
	}

	@Test
	public void shouldReturnInstanceOfDao() {
		when(container.instanceFor(DaoImpl.class)).thenReturn(new DaoImpl());
		assertThat(persistence.use(DaoImpl.class), instanceOf(DaoImpl.class));
	}
	
	@Test
	public void shouldReturnInstanceOfList() {
		when(container.instanceFor(DBList.class)).thenReturn(new DefaultDBList(null));
		assertThat(persistence.use(list()), instanceOf(DBList.class));
	}
	
	@Test
	public void shouldReturnListWith3Items() {
		when(container.instanceFor(DBList.class)).thenReturn(new DefaultDBList(testUtil.entityManager()));
		List<Model> find = persistence.use(list()).of(MyEntity.class).find();
		assertThat(find.size(), equalTo(3));
	}
	
	@Test
	public void shouldReturnListWithItem1() {
		when(container.instanceFor(DBLoad.class)).thenReturn(new DefaultDBLoad(testUtil.entityManager()));
		Model model = persistence.use(load()).of(MyEntity.class).find(1L);
		assertThat(model.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldSaveItem() {
		testUtil.beginTransaction();
		when(container.instanceFor(DBSave.class)).thenReturn(new DefaultDBSave(testUtil.entityManager()));
		MyEntity model = new MyEntity();
		model.name = "New";
		
		model = persistence.use(save()).of(MyEntity.class).persist(model);
		testUtil.commit();
		
		assertThat(model.getId(), notNullValue());
	}
	
	@Test
	public void shouldDeleteItem() {
		testUtil.beginTransaction();
		when(container.instanceFor(DBDelete.class)).thenReturn(new DefaultDBDelete(testUtil.entityManager()));
		MyEntity model = new MyEntity();
		model.setId(1L);
		
		persistence.use(delete()).of(MyEntity.class).delete(model);
		testUtil.commit();
	}

}