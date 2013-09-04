package org.dtelaroli.vplus.core.mock;

import org.dtelaroli.vplus.core.controller.Action;
import org.dtelaroli.vplus.core.controller.ActionDelete;
import org.dtelaroli.vplus.core.controller.ActionFacade;
import org.dtelaroli.vplus.core.controller.ActionList;
import org.dtelaroli.vplus.core.controller.ActionLoad;
import org.dtelaroli.vplus.core.controller.ActionSave;
import org.dtelaroli.vplus.core.controller.Controller;
import org.dtelaroli.vplus.core.exception.CrudException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
