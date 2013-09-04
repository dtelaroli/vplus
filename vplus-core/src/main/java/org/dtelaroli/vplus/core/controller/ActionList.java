package org.dtelaroli.vplus.core.controller;

import static org.dtelaroli.vplus.core.persistence.Persistences.list;

import java.util.List;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.model.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionList extends AbstractAction {

	public ActionList(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	public List<Model> operation() throws CrudException {
		return persistence().use(list()).of(type())
				.withLimit(limit)
				.withOrder(order)
				.withDirection(direction)
				.find();
	}

}