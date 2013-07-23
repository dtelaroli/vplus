package org.vplus.core.util;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.vplus.core.generics.Model;
import org.vplus.core.generics.Repository;

public class TypeGenericUtilTest {

	TypeGenericUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = TypeGenericUtil.create();
	}
	
	class Rep extends Repository<Model> {
		protected Rep(EntityManager entityManager) {
			super(entityManager);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void shouldReturnTypeFromSuperClass() {
		Repository<Model> clazz = new Rep(null);
		Class c = util.from(clazz.getClass());
		assertThat(c.isAssignableFrom(Model.class), is(true));
	}

}
