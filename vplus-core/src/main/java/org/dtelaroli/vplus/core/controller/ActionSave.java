package org.dtelaroli.vplus.core.controller;

import static org.dtelaroli.vplus.core.persistence.Persistences.save;

import org.dtelaroli.vplus.core.exception.CrudException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionSave extends AbstractAction {

	public ActionSave(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	protected Object operation() throws CrudException {
		validateModel();
		return persistence().use(save()).persist(model());
	}

}