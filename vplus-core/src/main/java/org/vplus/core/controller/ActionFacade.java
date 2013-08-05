package org.vplus.core.controller;

import org.vplus.core.model.Status;
import org.vplus.core.model.StatusFilter;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TypeUtil;

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