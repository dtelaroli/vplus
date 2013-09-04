package org.vplus.web;

import org.dtelaroli.vplus.core.controller.Crud;
import org.dtelaroli.vplus.core.controller.Scaffold;
import org.dtelaroli.vplus.core.model.StatusFilter;

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