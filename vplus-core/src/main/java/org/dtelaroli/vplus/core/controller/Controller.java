package org.dtelaroli.vplus.core.controller;


public interface Controller {
	
	<T extends Action> T use(Class<T> clazz);
	
}
