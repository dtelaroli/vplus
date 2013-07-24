package org.vplus.core.controller;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class DefaultController implements Controller {

	private Container container;

	public DefaultController(Container container) {
		this.container = container;
	}

	@Override
	public <T extends GenericController> T use(Class<T> type) {
		return container.instanceFor(type);
	}

}