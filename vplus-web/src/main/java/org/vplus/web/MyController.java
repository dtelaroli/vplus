package org.vplus.web;

import org.vplus.core.controller.CrudController;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.MyEntity;
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
	public void all() throws VPlusException {
		controller.list();
	}
	
	@Get("/myActive")
	public void active() throws VPlusException {
		filter.setActive();
		controller.list();
	}
	
	@Get("/myInactive")
	public void inactive() throws VPlusException {
		filter.setInactive();
		controller.list();
	}
	
	@Get("/myRemoved")
	public void removed() throws VPlusException {
		filter.setRemoved();
		controller.list();
	}

	@Get("/my/{model.id}")
	public void get(MyEntity model) throws VPlusException {
		controller.load(model);
	}
	
	@Post("/my")
	@Consumes(value = "application/json")
	public void add(MyEntity model) throws VPlusException {
		edit(model);
	}
	
	@Put("/my/{model.id}")
	@Consumes(value = "application/json")
	public void edit(MyEntity model) throws VPlusException {
		controller.save(model);
	}
	
	@Delete("/my/{model.id}")
	@Consumes(value = "application/json")
	public void save(MyEntity model) throws VPlusException {
		controller.delete(model);
	}	
	
}