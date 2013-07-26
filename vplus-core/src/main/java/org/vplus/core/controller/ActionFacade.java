package org.vplus.core.controller;

import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActionFacade {
	
	private Persistence persistence;
	private Result result;
	private Validator validator;

	public ActionFacade(Persistence persistence, Result result, Validator validator) {
		this.persistence = persistence;
		this.result = result;
		this.validator = validator;
	}

	public Persistence persistence() {
		return persistence;
	}

	public Result result() {
		return result;
	}
	
	public Validator validator() {
		return validator;
	}
	
}