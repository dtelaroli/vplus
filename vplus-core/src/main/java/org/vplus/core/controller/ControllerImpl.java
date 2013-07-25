package org.vplus.core.controller;

import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Dao;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ControllerImpl implements Controller {

private Class<? extends Model> clazz;
private Database database;
private View view;
private Class<? extends Dao> dao;
private Class<? extends br.com.caelum.vraptor.View> render;
	
	public ControllerImpl(Database database, View view) {
		this.database = database;
		this.view = view;
	}

	@Override
	public Controller of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return clazz;
	}

	public Controller withPersistence(Class<? extends Dao> dao) {
		this.dao = dao;
		return this;
	}

	@Override
	public Class<? extends Dao> persistence() {
		return dao;
	}

	@Override
	public Class<? extends br.com.caelum.vraptor.View> result() {
		return render;
	}

	@Override
	public Controller withResult(
			Class<? extends br.com.caelum.vraptor.View> view) {
		this.render = view;
		return this;
	}

}