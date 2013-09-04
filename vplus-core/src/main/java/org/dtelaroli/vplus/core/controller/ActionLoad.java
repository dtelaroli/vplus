package org.dtelaroli.vplus.core.controller;

import static org.dtelaroli.vplus.core.persistence.Persistences.load;

import org.dtelaroli.vplus.core.exception.CrudException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionLoad extends AbstractAction {

	public ActionLoad(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	public Object operation() throws CrudException {
		return persistence().use(load()).find(model());
	}

}