package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.persistence.Persistences;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefautControllerList implements ControllerList {

	private final Result result;
	private final Persistence persistence;
	private List<Model> list;

	public DefautControllerList(Result result, Persistence persistence) {
		this.result = result;
		this.persistence = persistence;
	}

	public <T extends Model> ControllerList of(Class<T> clazz) {
		list = persistence.use(Persistences.list()).of(clazz).find();
		return this;
	}

	@Override
	public void serialize() {
		result.use(json()).from(list).serialize();			
	}

}
