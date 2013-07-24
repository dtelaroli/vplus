package org.vplus.core.controller;

import org.vplus.core.generics.Model;


public interface ControllerList extends GenericController {
	
	void serialize();

	<T extends Model> ControllerList of(Class<T> clazz);

}
