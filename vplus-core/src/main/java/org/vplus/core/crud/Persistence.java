package org.vplus.core.crud;


public interface Persistence {
	
	<T extends DAO> T use(Class<T> dao);
	
}