package org.vplus.core.controller;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ControllerImpl implements Controller {

private Database database;
	
	public ControllerImpl(Database database) {
		this.database = database;
	}

	@Override
	public Database of(Class<? extends Model> clazz) {
		return database.of(clazz);	
	}

}