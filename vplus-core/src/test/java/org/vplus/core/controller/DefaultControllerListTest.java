package org.vplus.core.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DAO;
import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class DefaultControllerListTest {

	ControllerList controller;
	MockSerializationResult result;
	@Mock Persistence persistence;
	@Mock EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		persistence = new Persistence() {
			@SuppressWarnings("unchecked")
			@Override
			public <T extends DAO> T use(Class<T> dao) {
				return (T) new DBList() {
					@Override
					public DBList of(Class<? extends Model> clazz) {
						return this;
					}
					@SuppressWarnings("hiding")
					@Override
					public <T extends Model> List<T> find() {
						return (List<T>) Arrays.asList(new MyEntity(1L, "Entity"));
					}
				};
			}
		};
		
		controller = new DefautControllerList(result, persistence);
	}

	@Test
	public void shouldReturnType() {
		ControllerList c = controller.of(MyEntity.class);
		assertThat(c, instanceOf(ControllerList.class));
	}
	
	@Test
	public void shouldSerializeListJson() throws Exception {
		controller.of(MyEntity.class).serialize();
		assertThat(result.serializedResult(), containsString("{\"list\": [{\"id\":"));
	}

}
