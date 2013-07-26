package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.load;

import org.vplus.core.exception.VPlusException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionLoad extends AbstractAction {

	public ActionLoad(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	public Object operation() throws VPlusException {
		return persistence().use(load()).of(type()).find(model());
	}

}