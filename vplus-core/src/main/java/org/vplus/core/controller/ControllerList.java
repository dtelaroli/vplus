package org.vplus.core.controller;

import org.vplus.core.controller.DefautControllerList.ControllerListExecute;
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

public interface ControllerList extends GenericController {

	ControllerListExecute of(Class<? extends Model> clazz) throws VPlusException;
	
}