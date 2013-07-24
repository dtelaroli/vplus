package org.vplus.core.controller;


public interface Controller {
	
	<T extends GenericController> T use(Class<T> type);
	
}
