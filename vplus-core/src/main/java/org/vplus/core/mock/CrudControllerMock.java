package org.vplus.core.mock;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Arrays;

import org.vplus.core.controller.Action;
import org.vplus.core.controller.ActionFacade;
import org.vplus.core.controller.CrudController;
import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;

public class CrudControllerMock implements CrudController {

	private Class<? extends Model> type;
	private ActionFacade actionFacade;

	public CrudControllerMock() {
		this.actionFacade = new ActionFacadeMock();
	}

	@Override
	public CrudController of(Class<? extends Model> type) {
		this.type = type;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return type;
	}

	@Override
	public boolean isAsc() {
		return false;
	}

	@Override
	public CrudController asc() throws CrudException {
		return this;
	}

	@Override
	public CrudController desc() throws CrudException {
		return this;
	}

	@Override
	public CrudController withDirection(Direction order) throws CrudException {
		return this;
	}

	@Override
	public CrudController withOrder(String order) {
		return this;
	}

	@Override
	public String order() {
		return null;
	}

	@Override
	public Result result() {
		return actionFacade.result();
	}

	@Override
	public CrudController withLimit(Integer limit) {
		return this;
	}

	@Override
	public Integer limit() {
		return null;
	}

	@Override
	public void list() throws CrudException {
		result().use(json()).from(Arrays.asList(new Model(1L) {
			private static final long serialVersionUID = -6309663927636369622L;
		})).serialize();
	}

	@Override
	public void load(Model model) throws CrudException {
		result().use(json()).from(model).serialize();
	}

	@Override
	public void save(Model model) throws CrudException {
		result().use(json()).from(model).serialize();
	}

	@Override
	public void delete(Model model) throws CrudException {
		actionFacade.result().use(json()).from("ok", "message").serialize();
	}

	@Override
	public CrudController withStatus(Status status) {
		return this;
	}

	@Override
	public Status status() {
		return null;
	}

	@Override
	public <T extends Action> Action use(Class<T> action) {
		return null;
	}

}
