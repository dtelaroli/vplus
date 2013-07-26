package org.vplus.web;

import org.vplus.core.controller.ActionDelete;
import org.vplus.core.controller.ActionList;
import org.vplus.core.controller.ActionLoad;
import org.vplus.core.controller.ActionSave;
import org.vplus.core.controller.Controller;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/new")
public class NewController {

	private Controller controller;

	public NewController(Controller controller, Persistence persistence) {
		this.controller = controller;
	}

	@Get("/")
	public void index() throws VPlusException {
		controller.use(ActionList.class).of(NewModel.class).render();
	}

	@Get("/{entity.id}")
	public void get(NewModel entity) throws VPlusException {
		controller.use(ActionLoad.class).withModel(entity).of(NewModel.class).render();
	}
	
	@Post("/")
	@Consumes(value = "application/json")
	public void add(NewModel entity) throws VPlusException {
		controller.use(ActionSave.class).withModel(entity).render();
	}
	
	@Put("/{entity.id}")
	@Consumes(value = "application/json")
	public void edit(NewModel entity) throws VPlusException {
		controller.use(ActionSave.class).withModel(entity).render();
	}
	
	@Delete("/{entity.id}")
	@Consumes(value = "application/json")
	public void save(NewModel entity) throws VPlusException {
		controller.use(ActionDelete.class).withModel(entity).render();
	}	
	
}