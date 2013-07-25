package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.list;

import java.util.List;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionList extends AbstractAction {

	public ActionList(Persistence persistence, Result result) {
		super(persistence, result);
	}

	@Override
	public List<Model> persistence() throws VPlusException {
		return persistence.use(list()).of(clazz).find();
	}

}