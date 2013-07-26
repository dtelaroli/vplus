package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.delete;

import org.vplus.core.exception.VPlusException;

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
	protected Object operation() throws VPlusException {
		persistence().use(delete()).delete(model());
		return new Message("success");
	}

}
