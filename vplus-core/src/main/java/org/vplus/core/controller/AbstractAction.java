package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.representation;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;

public abstract class AbstractAction implements Action {

	protected Class<? extends Model> clazz;
	protected Persistence persistence;
	private Result result;

	public AbstractAction(Persistence persistence,
			Result result) {
		this.persistence = persistence;
		this.result = result;
	}

	public AbstractAction of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	public Class<? extends Model> type() {
		return clazz;
	}

	public void render() throws VPlusException {
		result.use(representation()).from(persistence()).serialize();
	}

	protected abstract Object persistence() throws VPlusException;
	
}
