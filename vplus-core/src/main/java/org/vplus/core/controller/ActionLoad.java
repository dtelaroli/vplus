package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.load;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionLoad extends AbstractAction {

	private Persistence persistence;
	private Model model;

	public ActionLoad(Persistence persistence, Result result) {
		super(persistence, result);
		this.persistence = persistence;
	}
	
	public ActionLoad withId(long id) {
		this.model = new MyEntity(id);
		return this;
	}

	@Override
	public Object persistence() throws VPlusException {
		return persistence.use(load()).of(type()).find(model);
	}

	protected Model model() {
		return model;
	}

}