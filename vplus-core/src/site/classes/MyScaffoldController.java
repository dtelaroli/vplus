package org.vplus.web;

import org.vplus.core.controller.CrudController;
import org.vplus.core.controller.Scaffold;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.deserialization.gson.ConsumesTypes;

@Resource
@Path("/my")
@ConsumesTypes(MyEntity.class)
public class MyController extends Scaffold<MyEntity> {

	public MyController(CrudController controller) {
		super(controller);
	}

}