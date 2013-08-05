package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class CrudControllerImpl implements CrudController {

	private Class<? extends Model> clazz;
	private Controller controller;
	private AbstractAction action;
	private Direction direction;
	private String order;
	private Integer limit;
	private Status status;

	public CrudControllerImpl(Controller controller) {
		this.controller = controller;
		this.action = ActionNull.create();
		this.direction = Direction.NULL;
	}
	
	protected static class ActionNull extends AbstractAction {
		public ActionNull(ActionFacade actionFacade) {
			super(actionFacade);
		}
		
		public static AbstractAction create() {
			return new ActionNull(null);
		}
		
		@Override
		protected Object operation() throws CrudException {	return null; }
		
		@Override
		public Result result() { return null; }
		
		@Override
		public AbstractAction withOrder(String order) {	return null; }
		
		@Override
		public AbstractAction withDirection(Direction direction) { return null; }
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
	public CrudController asc() throws CrudException {
		this.direction = Direction.ASC;
		return this;
	}

	@Override
	public boolean isAsc() {
		return direction.isAsc();
	}

	@Override
	public CrudController desc() throws CrudException {
		this.direction = Direction.DESC;
		return this;
	}
	
	@Override
	public CrudController withDirection(Direction direction) throws CrudException {
		this.direction = direction;
		return this;
	}
	
	@Override
	public CrudController withOrder(String order) {
		this.order = order;
		return this;
	}
	
	@Override
	public String order() {
		return order;
	}
	
	public void actionExecute() throws CrudException {
		action.withDirection(direction).withOrder(order).withLimit(limit).withStatus(status()).of(type()).render();
	}

	@Override
	public Result result() {
		return action.result();
	}

	@Override
	public CrudController withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	@Override
	public Integer limit() {
		return limit;
	}
	

	@Override
	public void list() throws CrudException {
		action = controller.use(Controllers.list());
		actionExecute();
	}
	
	@Override
	public void load(Model model) throws CrudException {
		action = controller.use(Controllers.load()).withModel(model);
		actionExecute();		
	}
	
	@Override
	public void save(Model model) throws CrudException {
		action = controller.use(Controllers.save()).withModel(model);
		actionExecute();
	}
	
	@Override
	public void delete(Model model) throws CrudException {
		action = controller.use(Controllers.delete()).withModel(model);
		actionExecute();
	}

	@Override
	public CrudController withStatus(Status status) {
		this.status = status;
		return this;
	}

	@Override
	public Status status() {
		return status;
	}

	@Override
	public <T extends Action> Action use(Class<T> action) {
		return controller.use(action);
	}

}