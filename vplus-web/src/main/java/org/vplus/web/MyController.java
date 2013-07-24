package org.vplus.web;

import static org.vplus.core.persistence.Persistences.save;

import org.vplus.core.controller.Controller;
import org.vplus.core.controller.ControllerList;
import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class MyController {

	private Controller controller;

	public MyController(Controller controller, Persistence persistence) {
		this.controller = controller;
		persistence.use(save()).persist(new MyEntity());
	}

	@Get("/my")
	public void index() {
		controller.use(ControllerList.class).serialize();
	}
	
	@Get("/my/{id}")
	public void get(long id) {
	}
}
