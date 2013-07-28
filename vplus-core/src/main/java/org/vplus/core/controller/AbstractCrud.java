package org.vplus.core.controller;

import org.vplus.core.deserialization.ConsumesType;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

public abstract class AbstractCrud<T extends Model> {

	private CrudController controller;

	public AbstractCrud(CrudController controller) {
		ConsumesType consumesTypes = getClass().getAnnotation(ConsumesType.class);
		this.controller = controller.of(consumesTypes.value());
	}
	
	@Get("/all")
	public void all() throws CrudException {
		controller.list();
	}
	
	@Get({"/order/{order}/dir/{direction}/limit/{limit}"})
	public void order(String order, Order direction, Integer limit) throws CrudException {
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