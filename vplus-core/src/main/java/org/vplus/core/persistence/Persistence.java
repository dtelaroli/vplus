package org.vplus.core.persistence;


public interface Persistence {
	
	<T extends DAO> T use(Class<T> dao);
	
}