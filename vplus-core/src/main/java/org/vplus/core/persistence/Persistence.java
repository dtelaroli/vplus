package org.vplus.core.persistence;


public interface Persistence {
	
	<T extends Dao> T use(Class<T> dao);
	
}