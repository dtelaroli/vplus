package org.dtelaroli.vplus.core.mock;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Arrays;

import org.dtelaroli.vplus.core.controller.Action;
import org.dtelaroli.vplus.core.controller.ActionFacade;
import org.dtelaroli.vplus.core.controller.Crud;
import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;

public class CrudMock implements Crud {

	private Class<? extends Model> type;
	private ActionFacade actionFacade;

	public CrudMock() {
		this.actionFacade = new ActionFacadeMock();
	}

	@Override
	public Crud of(Class<? extends Model> type) {
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
	public Crud asc() throws CrudException {
		return this;
	}

	@Override
	public Crud desc() throws CrudException {
		return this;
	}

	@Override
	public Crud withDirection(Direction order) throws CrudException {
		return this;
	}

	@Override
	public Crud withOrder(String order) {
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
	public Crud withLimit(Integer limit) {
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
	public Crud withStatus(Status status) {
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
