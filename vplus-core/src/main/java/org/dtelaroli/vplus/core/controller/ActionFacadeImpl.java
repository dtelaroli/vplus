package org.dtelaroli.vplus.core.controller;

import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.model.StatusFilter;
import org.dtelaroli.vplus.core.persistence.Persistence;
import org.dtelaroli.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ActionFacadeImpl implements ActionFacade {
	
	private Persistence persistence;
	private Result result;
	private Validator validator;
	private TypeUtil typeUtil;
	private StatusFilter filter;

	public ActionFacadeImpl(Persistence persistence, Result result, Validator validator,
			TypeUtil typeUtil, StatusFilter filter) {
		this.persistence = persistence;
		this.result = result;
		this.validator = validator;
		this.typeUtil = typeUtil;
		this.filter = filter;
	}

	@Override
	public Persistence persistence() {
		return persistence;
	}

	@Override
	public Result result() {
		return result;
	}
	
	@Override
	public Validator validator() {
		return validator;
	}

	@Override
	public TypeUtil typeUtil() {
		return typeUtil;
	}

	@Override
	public StatusFilter filter() {
		return filter;
	}

	@Override
	public void setFilter(Status status) {
		filter.setStatus(status);
	}
	
}