package org.vplus.core.persistence;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.Container;

@Component
public class PersistenceImpl implements Persistence {

	private final Container container;

	public PersistenceImpl(Container container) {
		this.container = container;
	}

	@Override
	public <T extends Dao> T use(Class<T> type) {
		return container.instanceFor(type);
	}

}