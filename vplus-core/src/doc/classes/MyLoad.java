package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.load;

import org.vplus.core.exception.CrudException;
import org.vplus.core.persistence.MyFind;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class MyLoad implements AbstractAction {

	public MyLoad(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	public Object operation() throws CrudException {
		return persistence().use(MyFind.class).find();
	}

}