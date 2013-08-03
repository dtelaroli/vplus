package org.vplus.core.controller;

import net.vidageek.mirror.dsl.Mirror;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

public abstract class Scaffold<T extends Model> {

	private CrudController controller;

	
	public Scaffold(CrudController controller) {
		this.controller = controller;
	}

	@SuppressWarnings("unchecked")
	protected Class<? extends Model> getType() {
		return (Class<? extends Model>) new Mirror().on(getClass()).reflect().parentGenericType().atPosition(0);
	}

	@Get("")
	public void all() throws CrudException {
		controller.of(getType()).list();
	}
	
	@Get("/order/{order}/dir/{direction}/limit/{limit}")
	public void all(String order, Direction direction, Integer limit) throws CrudException {
		controller.withOrder(order).withDirection(direction).withLimit(limit).list();
	}
	
	@Get("/{model.id}")
	public void get(T model) throws CrudException {
		controller.load(model);
	}
	
	@Post("")
	@Consumes(value = "application/json")
	public void add(T model) throws CrudException {
		edit(model);
	}
	
	@Put("/{model.id}")
	@Consumes(value = "application/json")
	public void edit(T model) throws CrudException {
		controller.save(model);
	}
	
	@Delete("/{model.id}")
	public void remove(T model) throws CrudException {
		controller.delete(model);
	}

}