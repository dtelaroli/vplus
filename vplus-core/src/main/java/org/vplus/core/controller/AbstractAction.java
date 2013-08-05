package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public abstract class AbstractAction implements Action {

	private ActionFacade actionFacade;
	private Class<? extends Model> clazz;
	private Model model;
	protected String order;
	protected Direction direction;
	protected Integer limit;
	
	public AbstractAction(ActionFacade actionFacade) {
		this.actionFacade = actionFacade;
	}

	public AbstractAction of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	public Class<? extends Model> type() {
		return clazz;
	}
	
	public Persistence persistence() {
		return actionFacade.persistence();
	}

	public Result result() {
		return actionFacade.result();
	}
	
	public Validator validator() {
		return actionFacade.validator();
	}

	public void render() throws CrudException {
		final Object operation = operation();
		validateOperation(operation);
		
		Model m = getModel(operation);
		result().use(json()).withoutRoot().from(operation)
		.include(m.includes()).exclude(m.excludes()).serialize();
	}

	protected Model getModel(Object operation) {
		TypeUtil typeUtil = actionFacade.typeUtil().of(Model.class);
		if(typeUtil.compare(operation.getClass())) {
			return castToModel(operation);
		}
		else if(typeUtil.isListFrom(operation)) {
			return castToList(operation);
		}
		return new Model() {
			private static final long serialVersionUID = -8447095386623014215L;
		};
	}

	@SuppressWarnings("unchecked")
	private Model castToList(Object object) {
		return ((List<Model>)object).get(0);
	}

	private Model castToModel(Object object) {
		return (Model)object;
	}

	private void validateOperation(final Object object) {
		validator().checking(new Validations() {
			{
				that(object, notNullValue(), i18n("notNull"), "object");
			}
		});
		dispatchError();
	}

	private void dispatchError() {
		validator().onErrorSendBadRequest();
	}

	protected void validateModel() {
		validator().validate(model);
		dispatchError();
	}
	
	public AbstractAction withModel(Model model) {
		this.model = model;
		return this;
	}

	protected Model model() {
		return model;
	}

	protected abstract Object operation() throws CrudException;

	public AbstractAction withOrder(String order) {
		this.order = order;
		return this;
	}

	public AbstractAction withDirection(Direction direction) {
		this.direction = direction;
		return this;
	}

	public AbstractAction withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}
	
	public Integer limit() {
		return limit;
	}

	public AbstractAction withStatus(Status status) {
		actionFacade.setFilter(status);
		return this;
	}
	
}