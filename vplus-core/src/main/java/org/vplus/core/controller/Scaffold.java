package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.deserialization.gson.ConsumesTypes;

public abstract class Scaffold<T extends Model> {

	private CrudController controller;

	@SuppressWarnings("unchecked")
	public Scaffold(CrudController controller) {
		ConsumesTypes consumesTypes = getAnnotation();
		this.controller = controller.of((Class<? extends Model>) consumesTypes.value()[0]);
	}

	private ConsumesTypes getAnnotation() {
		ConsumesTypes annotation = getClass().getAnnotation(ConsumesTypes.class);
		if(annotation == null) {
			throw new IllegalStateException("Scaffold Controller should be annoted with @ConsumesTypes");
		}
		return annotation;
	}
	
	@Get("/")
	public void all() throws CrudException {
		controller.list();
	}
	
	@Get("/order/{order}/dir/{direction}/limit/{limit}")
	public void all(String order, Order direction, Integer limit) throws CrudException {
		controller.withOrder(order).withDirection(direction).withLimit(limit).list();
	}
	
	@Get("/{model.id}")
	public void get(T model) throws CrudException {
		controller.load(model);
	}
	
	@Post("/")
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
	@Consumes(value = "application/json")
	public void remove(T model) throws CrudException {
		controller.delete(model);
	}

}