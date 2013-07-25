package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

import org.vplus.core.exeption.VPlusException;
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
	private Class<? extends Model> clazz;

	public DefautControllerList(Result result, Persistence persistence) {
		this.result = result;
		this.persistence = persistence;
		list = new ArrayList<>();
	}

	public <T extends Model> ControllerList of(Class<T> clazz) throws VPlusException {
		this.clazz = clazz;
		return this;
	}

	@Override
	public void serialize() throws VPlusException {
		list = persistence.use(Persistences.list()).of(clazz).find();
		result.use(json()).from(list).serialize();			
	}

}
