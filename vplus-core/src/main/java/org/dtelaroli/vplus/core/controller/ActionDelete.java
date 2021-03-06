package org.dtelaroli.vplus.core.controller;

import static org.dtelaroli.vplus.core.persistence.Persistences.delete;

import org.dtelaroli.vplus.core.exception.CrudException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionDelete extends AbstractAction {

	public ActionDelete(ActionFacade actionFacade) {
		super(actionFacade);
	}
	
	class Message {
		String info;
		public Message(String confirmation) {
			this.info = confirmation;
		}
	}

	@Override
	protected Object operation() throws CrudException {
		persistence().use(delete()).delete(model());
		return new Message("success");
	}

}
