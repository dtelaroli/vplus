package org.dtelaroli.vplus.core.controller;

import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.model.StatusFilter;
import org.dtelaroli.vplus.core.persistence.Persistence;
import org.dtelaroli.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

public interface ActionFacade {

	Persistence persistence();

	Result result();

	Validator validator();

	TypeUtil typeUtil();

	StatusFilter filter();

	void setFilter(Status status);

}