package org.vplus.web;

import static org.vplus.core.persistence.Persistences.save;

import org.vplus.core.controller.ActionList;
import org.vplus.core.controller.ActionLoad;
import org.vplus.core.controller.Controller;
import org.vplus.core.exeption.VPlusException;
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
	public void index() throws VPlusException {
		controller.use(ActionList.class).of(MyEntity.class).render();
	}

	@Get("/my/{id}")
	public void get(long id) throws VPlusException {
		controller.use(ActionLoad.class).withId(id).of(MyEntity.class).render();
	}
}
