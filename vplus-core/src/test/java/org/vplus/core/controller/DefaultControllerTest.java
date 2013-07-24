package org.vplus.core.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.ioc.Container;

public class DefaultControllerTest {

	DefaultController controller;
	@Mock Container container;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new DefaultController(container);
	}
	
	interface MyController extends GenericController {
	}
	class MyControl implements MyController {
	}

	@Test
	public void shouldReturnMyControlIfB() {
		when(container.instanceFor(MyController.class)).thenReturn(new MyControl());
		
		MyController use = controller.use(MyController.class);
		assertThat(use, instanceOf(MyControl.class));
	}

}
