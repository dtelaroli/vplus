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
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DBListImpl.DBListExecute;
import org.vplus.core.util.TestUtil;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class PersistenceImplTest {
	
	Persistence persistence;
	@Mock Container container;
	private TestUtil testUtil;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		persistence = new PersistenceImpl(container);
		testUtil = TestUtil.create();
		testUtil.from(MyEntity.class).init();
	}
	
	@After
	public void tearDown() throws DatabaseUnitException {
		testUtil.cleanAndDestroy();
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
		when(container.instanceFor(DBList.class)).thenReturn(new DBListImpl(null));
		assertThat(persistence.use(list()), instanceOf(DBList.class));
	}
	
	@Test
	public void shouldReturnListWith3Items() throws VPlusException {
		when(container.instanceFor(DBList.class)).thenReturn(new DBListImpl(new DBListExecute(testUtil.entityManager())));
		List<Model> find = persistence.use(list()).of(MyEntity.class).find();
		assertThat(find.size(), equalTo(3));
	}
	
	@Test
	public void shouldReturnListWithItem1() throws VPlusException {
		when(container.instanceFor(DBLoad.class)).thenReturn(new DBLoadImpl(testUtil.entityManager()));
		Model model = persistence.use(load()).of(MyEntity.class).find(1L);
		assertThat(model.getId(), equalTo(1L));
	}
	
	@Test
	public void shouldSaveItem() {
		testUtil.beginTransaction();
		when(container.instanceFor(DBSave.class)).thenReturn(new DBSaveImpl(testUtil.entityManager()));
		MyEntity model = new MyEntity();
		model.name = "New";
		
		model = persistence.use(save()).persist(model);
		testUtil.commit();
		
		assertThat(model.getId(), notNullValue());
	}
	
	@Test
	public void shouldDeleteItem() {
		testUtil.beginTransaction();
		when(container.instanceFor(DBDelete.class)).thenReturn(new DBDeleteImpl(testUtil.entityManager()));
		MyEntity model = new MyEntity();
		model.setId(1L);
		
		persistence.use(delete()).delete(model);
		testUtil.commit();
	}

}