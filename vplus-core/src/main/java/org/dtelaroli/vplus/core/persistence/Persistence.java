package org.dtelaroli.vplus.core.persistence;


public interface Persistence {
	
	<T extends Dao> T use(Class<T> dao);
	
}