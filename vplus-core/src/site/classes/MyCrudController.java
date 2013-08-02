package org.vplus.web;

import org.vplus.core.controller.CrudController;
import org.vplus.core.controller.CrudSignature;
import org.vplus.core.controller.Scaffold;
import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.MyEntity;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.deserialization.gson.ConsumesTypes;

@Resource
@Path("/my")
public class MyController implements CrudSignature<MyEntity> {

	private CrudController controller;

	public MyController(CrudController controller) {
		this.controller = controller.of(MyEntity.class);
	}
	
	@Override
	@Get("/")
	public void all() throws CrudException {
		controller.list();
	}
	
	@Override
	@Get("/{model.id}")
	public void get(MyEntity model) throws CrudException {
		controller.load(model);
	}
	
	@Override
	@Post("/")
	@Consumes(value = "application/json")
	public void add(MyEntity model) throws CrudException {
		edit(model);
	}

	@Override
	@Put("/{model.id}")
	@Consumes(value = "application/json")
	public void edit(MyEntity model) throws CrudException {
		controller.save(model);
	}
	
	@Override
	@Delete("/{model.id}")
	@Consumes(value = "application/json")
	public void remove(MyEntity model) throws CrudException {
		controller.delete(model);
	}
}