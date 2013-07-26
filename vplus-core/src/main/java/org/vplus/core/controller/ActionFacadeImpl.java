package org.vplus.core.controller;

import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TypeUtil;

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

	public ActionFacadeImpl(Persistence persistence, Result result, Validator validator,
			TypeUtil typeUtil) {
		this.persistence = persistence;
		this.result = result;
		this.validator = validator;
		this.typeUtil = typeUtil;
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
	
}