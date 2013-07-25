package org.vplus.core.controller;

import org.vplus.core.generics.Model;


public interface Controller {
	
	Database of(Class<? extends Model> clazz);
	
}
