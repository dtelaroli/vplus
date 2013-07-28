package org.vplus.web;

import org.vplus.core.controller.AbstractCrud;
import org.vplus.core.controller.CrudController;
import org.vplus.core.deserialization.ConsumesType;
import org.vplus.core.generics.MyEntity;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/my")
@ConsumesType(MyEntity.class)
public class MyController extends AbstractCrud<MyEntity> {

	public MyController(CrudController controller) {
		super(controller);
	}

}