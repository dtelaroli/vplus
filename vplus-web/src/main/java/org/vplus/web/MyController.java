package org.vplus.web;

import org.vplus.core.controller.CrudController;
import org.vplus.core.controller.Scaffold;
import org.vplus.core.generics.StatusFilter;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/my")
public class MyController extends Scaffold<MyEntity> {

	public MyController(CrudController controller, StatusFilter filter) {
		super(controller);
		filter.disableFilter();
	}

}