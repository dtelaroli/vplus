package org.vplus.core.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.controller.DefautControllerList.ControllerListExecute;
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DefaultDBList.DBListExecute;
import org.vplus.core.persistence.MyEntity;

import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class DefaultControllerListTest {

	ControllerList controller;
	MockSerializationResult result;
	@Mock ControllerListExecute controllerListExecute;
	@Mock DBListExecute dbListExecute;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		result = new MockSerializationResult();
		controller = new ControllerList() {
			@Override
			public ControllerListExecute of(Class<? extends Model> clazz)
					throws VPlusException {
				controllerListExecute = spy(new ControllerListExecute(result, null));
				List<Model> list = new ArrayList<Model>();
				list.add(new MyEntity(1L, "Test"));
				doReturn(list).when(controllerListExecute).getList();
				return controllerListExecute;
			}
		};
	}

	@Test
	public void shouldReturnType() throws VPlusException {
		ControllerListExecute c = controller.of(MyEntity.class);
		assertThat(c, instanceOf(ControllerListExecute.class));
	}
	
	@Test
	public void shouldSerializeListJson() throws Exception {
		controller.of(MyEntity.class).serialize();
		assertThat(result.serializedResult(), containsString("{\"list\": [{\"id\":"));
	}

}
