package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class CrudImpl implements Crud {

	private Class<? extends Model> clazz;
	private Controller controller;
	private AbstractAction action;
	private Direction direction;
	private String order;
	private Integer limit;
	private Status status;

	public CrudImpl(Controller controller) {
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
	public Crud of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return clazz;
	}

	@Override
	public Crud asc() throws CrudException {
		this.direction = Direction.ASC;
		return this;
	}

	@Override
	public boolean isAsc() {
		return direction.isAsc();
	}

	@Override
	public Crud desc() throws CrudException {
		this.direction = Direction.DESC;
		return this;
	}
	
	@Override
	public Crud withDirection(Direction direction) throws CrudException {
		this.direction = direction;
		return this;
	}
	
	@Override
	public Crud withOrder(String order) {
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
	public Crud withLimit(Integer limit) {
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
	public Crud withStatus(Status status) {
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