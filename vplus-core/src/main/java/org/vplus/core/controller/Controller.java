package org.vplus.core.controller;

import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Dao;

import br.com.caelum.vraptor.View;

public interface Controller {
	
	Controller of(Class<? extends Model> model);
	Class<? extends Model> type();

	Class<? extends Dao> persistence();
	Controller withPersistence(Class<? extends Dao> dao);
	
	Class<? extends View> result();
	Controller withResult(Class<? extends View> view);
	
}
