package org.dtelaroli.vplus.core.controller;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class ControllerImpl implements Controller {

	private Container container;
	
	public ControllerImpl(Container container) {
		this.container = container;
	}

	@Override
	public <T extends Action> T use(Class<T> clazz) {
		return (T) container.instanceFor(clazz);
	}

}