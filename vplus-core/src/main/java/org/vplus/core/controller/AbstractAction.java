package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.representation;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

public abstract class AbstractAction implements Action {

	private ActionFacade actionFacade;
	private Class<? extends Model> clazz;
	private Model model;
	
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

	public void render() throws VPlusException {
		final Object operation = operation();
		validateOperation(operation);
		
		result().use(representation()).from(operation)
		.include(getIncludes(operation)).serialize();
	}

	protected String[] getIncludes(Object operation) {
		TypeUtil typeUtil = actionFacade.typeUtil().of(Model.class);
		if(typeUtil.compare(operation.getClass())) {
			return castToModel(operation);
		}
		else if(typeUtil.isListFrom(operation)) {
			List<Model> list = castToList(operation);
			return list.get(0).getIncludes();
		}
		return new String[]{};
	}

	@SuppressWarnings("unchecked")
	private List<Model> castToList(Object operation) {
		return (List<Model>)operation;
	}

	private String[] castToModel(Object operation) {
		return ((Model)operation).getIncludes();
	}

	private void validateOperation(final Object operation) {
		validator().checking(new Validations() {
			{
				that(operation, notNullValue(), i18n("notNull"), "operation");
			}
		});
		dispatchError();
	}

	private void dispatchError() {
		validator().onErrorUse(representation())
		.from(validator().getErrors(), "errors").serialize();
	}

	protected void validateModel() {
		validator().validate(model);
		dispatchError();
	}
	
	public AbstractAction withModel(Model model) {
		this.model = model;
		return this;
	}

	public Model model() {
		return model;
	}

	protected abstract Object operation() throws VPlusException;
	
}
