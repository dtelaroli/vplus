package org.vplus.web;

import org.vplus.core.controller.Crud;
import org.vplus.core.controller.Scaffold;
import org.vplus.core.model.StatusFilter;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/my")
public class MyController extends Scaffold<MyEntity> {

	public MyController(Crud controller, StatusFilter filter) {
		super(controller);
		filter.disableFilter();
	}
	
}