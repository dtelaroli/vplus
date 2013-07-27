package org.vplus.web;

import org.vplus.core.controller.CrudController;
import org.vplus.core.controller.Order;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.MyEntity;
import org.vplus.core.generics.Status;
import org.vplus.core.persistence.StatusFilter;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;

@Resource
public class MyController {

	private CrudController controller;
	private StatusFilter filter;

	public MyController(CrudController controller, StatusFilter filter) {
		this.controller = controller;
		this.filter = filter;
		this.controller = controller.of(MyEntity.class);
	}

	@Get("/my")
	public void all() throws CrudException {
		controller.list();
	}
	
	@Get({"/myOrder/{order}/{direction}/{limit}", "/myOrder/{order}/{direction}"})
	public void order(String order, Order direction, Integer limit) throws CrudException {
		controller.withOrder(order).withDirection(direction).withLimit(limit).list();
	}
	
	@Get("/myFilter/{status}")
	public void active(Status status) throws CrudException {
		filter.setStatus(status);
		controller.list();
	}
	
	@Get("/myActive")
	public void active() throws CrudException {
		filter.setActive();
		controller.list();
	}
	
	@Get("/myInactive")
	public void inactive() throws CrudException {
		filter.setInactive();
		controller.list();
	}
	
	@Get("/myRemoved")
	public void removed() throws CrudException {
		filter.setRemoved();
		controller.list();
	}

	@Get("/my/{model.id}")
	public void get(MyEntity model) throws CrudException {
		controller.load(model);
	}
	
	@Post("/my")
	@Consumes(value = "application/json")
	public void add(MyEntity model) throws CrudException {
		edit(model);
	}
	
	@Put("/my/{model.id}")
	@Consumes(value = "application/json")
	public void edit(MyEntity model) throws CrudException {
		controller.save(model);
	}
	
	@Delete("/my/{model.id}")
	@Consumes(value = "application/json")
	public void save(MyEntity model) throws CrudException {
		controller.delete(model);
	}	
	
}