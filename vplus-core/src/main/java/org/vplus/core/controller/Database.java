package org.vplus.core.controller;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

public interface Database extends Action {

	Database of(Class<? extends Model> dao);

	View find() throws VPlusException;

	View find(long id) throws VPlusException;
	
}