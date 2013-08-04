package org.vplus.core.mock;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.controller.Action;
import org.vplus.core.controller.ActionDelete;
import org.vplus.core.controller.ActionFacade;
import org.vplus.core.controller.ActionList;
import org.vplus.core.controller.ActionLoad;
import org.vplus.core.controller.ActionSave;
import org.vplus.core.controller.Controller;
import org.vplus.core.exception.CrudException;

public class ControllerMock implements Controller {

	@Mock private ActionFacade actionFacade;

	public ControllerMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Override
	public <T extends Action> T use(Class<T> action) {
		try {
			return instanceAction(action);
		} catch (CrudException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private <T extends Action> T instanceAction(Class<T> action) throws CrudException {
		if(action.isAssignableFrom(ActionList.class)) {
			return (T) new ActionList(actionFacade);
		}
		
		if(action.isAssignableFrom(ActionLoad.class)) {
			actionFacade = new ActionFacadeMock();
			return ((T) new ActionLoad(actionFacade));
		}
		
		if(action.isAssignableFrom(ActionSave.class)) {
			actionFacade = new ActionFacadeMock();
			return ((T) new ActionSave(actionFacade));
		}
		
		if(action.isAssignableFrom(ActionDelete.class)) {
			actionFacade = new ActionFacadeMock();
			return ((T) new ActionDelete(actionFacade));
		}
		throw new CrudException("Mock could instanciate action " + action);
	}

}
