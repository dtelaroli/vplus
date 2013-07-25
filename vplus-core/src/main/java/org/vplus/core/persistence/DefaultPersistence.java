package org.vplus.core.persistence;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class DefaultPersistence implements Persistence {

	private final Container container;

	public DefaultPersistence(Container container) {
		this.container = container;
	}

	@Override
	public <T extends Dao> T use(Class<T> type) {
		return container.instanceFor(type);
	}

}