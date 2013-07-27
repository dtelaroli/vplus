package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.list;

import java.util.List;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionList extends AbstractAction {

	public ActionList(ActionFacade actionFacade) {
		super(actionFacade);
	}

	@Override
	public List<Model> operation() throws CrudException {
		return persistence().use(list()).of(type()).withOrder(order).withDirection(direction).find();
	}

}