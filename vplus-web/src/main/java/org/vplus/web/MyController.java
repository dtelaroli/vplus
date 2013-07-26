package org.vplus.web;

import org.vplus.core.controller.ActionDelete;
import org.vplus.core.controller.ActionList;
import org.vplus.core.controller.ActionLoad;
import org.vplus.core.controller.ActionSave;
import org.vplus.core.controller.Controller;
import org.vplus.core.exception.VPlusException;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;

@Resource
public class MyController {

	private Controller controller;

	public MyController(Controller controller, Persistence persistence) {
		this.controller = controller;
	}

	@Get("/my")
	public void index() throws VPlusException {
		controller.use(ActionList.class).of(MyEntity.class).render();
	}

	@Get("/my/{entity.id}")
	public void get(MyEntity entity) throws VPlusException {
		controller.use(ActionLoad.class).withModel(entity).of(MyEntity.class).render();
	}
	
	@Post("/my")
	@Consumes(value = "application/json")
	public void add(MyEntity entity) throws VPlusException {
		edit(entity);
	}
	
	@Put("/my/{entity.id}")
	@Consumes(value = "application/json")
	public void edit(MyEntity entity) throws VPlusException {
		controller.use(ActionSave.class).withModel(entity).render();
	}
	
	@Delete("/my/{entity.id}")
	@Consumes(value = "application/json")
	public void save(MyEntity entity) throws VPlusException {
		controller.use(ActionDelete.class).withModel(entity).render();
	}	
	
}