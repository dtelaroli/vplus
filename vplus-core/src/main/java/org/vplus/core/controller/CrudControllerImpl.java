package org.vplus.core.controller;

import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class CrudControllerImpl implements CrudController {

	private Class<? extends Model> clazz;
	private Controller controller;
	private AbstractAction action;

	public CrudControllerImpl(Controller controller) {
		this.controller = controller;
		this.action = ActionNull.create();
	}
	
	@Override
	public CrudController of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return clazz;
	}

	@Override
	public void list() throws VPlusException {
		action = controller.use(Controllers.list());
		actionExecute();
	}

	@Override
	public void load(Model model) throws VPlusException {
		action = controller.use(Controllers.load()).withModel(model);
		actionExecute();		
	}
	
	@Override
	public void save(Model model) throws VPlusException {
		action = controller.use(Controllers.save()).withModel(model);
		actionExecute();
	}
	
	@Override
	public void delete(Model model) throws VPlusException {
		action = controller.use(Controllers.delete()).withModel(model);
		actionExecute();
	}
	
	public void actionExecute() throws VPlusException {
		action.of(type()).render();
	}

	@Override
	public Result result() {
		return action.result();
	}
	
	static class ActionNull extends AbstractAction {

		public ActionNull(ActionFacade actionFacade) {
			super(actionFacade);
		}

		public static AbstractAction create() {
			return new ActionNull(null);
		}

		@Override
		protected Object operation() throws VPlusException {
			return null;
		}
		
		@Override
		public Result result() {
			return null;
		}
	}

}