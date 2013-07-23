package org.vplus.core.crud;

import br.com.caelum.vraptor.ioc.Container;

public class DefaultPersistence implements Persistence {

	private final Container container;

	public DefaultPersistence(Container container) {
		this.container = container;
	}

	@Override
	public <T extends DAO> T use(Class<T> type) {
		return container.instanceFor(type);
	}

}