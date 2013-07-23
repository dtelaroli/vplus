package org.vplus.core.crud;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class DefaultPersistenceTest {
	
	Persistence persistence;
	@Mock Container container;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		persistence = new DefaultPersistence(container);
	}
	
	class DaoImpl implements DAO {
		@Override
		public DefaultList of(Class<? extends Model> clazz) {
			return null;
		}
	}

	@Test
	public void shouldReturnInstanceOfDao() {
		when(container.instanceFor(DaoImpl.class)).thenReturn(new DaoImpl());
		assertThat(persistence.use(DaoImpl.class), instanceOf(DaoImpl.class));
	}

}