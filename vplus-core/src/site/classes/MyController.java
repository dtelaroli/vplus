package org.vplus.web;

import static org.vplus.core.controller.Controllers.list;

import org.vplus.core.controller.Controller;
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
public class MyController {

	private Controller controller;

	public MyController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	@Get("/")
	public void all() throws CrudException {
		controller.use(list()).of(MyEntity.class).render();
	}
	
	@Override
	@Get("/{model.id}")
	public void get(MyEntity model) throws CrudException {
		controller.use(MyLoad.class).render();
	}
	
}