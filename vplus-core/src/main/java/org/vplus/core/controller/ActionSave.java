package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.save;

import org.vplus.core.exception.VPlusException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionSave extends AbstractAction {

	public ActionSave(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	protected Object operation() throws VPlusException {
		validateModel();
		return persistence().use(save()).persist(model());
	}

}