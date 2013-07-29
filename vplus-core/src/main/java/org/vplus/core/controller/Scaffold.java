package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.deserialization.gson.ConsumesTypes;

public abstract class Scaffold<T extends Model> implements CrudSignature<T>{

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

	@Override
	@Get("/")
	public void all() throws CrudException {
		controller.list();
	}
	
	@Get("/order/{order}/dir/{direction}/limit/{limit}")
	public void all(String order, Direction direction, Integer limit) throws CrudException {
		controller.withOrder(order).withDirection(direction).withLimit(limit).list();
	}
	
	@Override
	@Get("/{model.id}")
	public void get(T model) throws CrudException {
		controller.load(model);
	}
	
	@Override
	@Post("/")
	@Consumes(value = "application/json")
	public void add(T model) throws CrudException {
		edit(model);
	}
	
	@Override
	@Put("/{model.id}")
	@Consumes(value = "application/json")
	public void edit(T model) throws CrudException {
		controller.save(model);
	}
	
	@Override
	@Delete("/{model.id}")
	@Consumes(value = "application/json")
	public void remove(T model) throws CrudException {
		controller.delete(model);
	}

}