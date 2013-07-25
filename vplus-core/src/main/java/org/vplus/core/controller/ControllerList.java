package org.vplus.core.controller;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;


public interface ControllerList extends GenericController {
	
	void serialize() throws VPlusException;

	<T extends Model> ControllerList of(Class<T> clazz) throws VPlusException;

}