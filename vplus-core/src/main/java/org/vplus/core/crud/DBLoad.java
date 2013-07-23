package org.vplus.core.crud;

import org.vplus.core.generics.Model;

public interface DBLoad extends DAO {

	<T extends Model> T find(Long id);

	Class<? extends Model> type();
	
	DBLoad of(Class<? extends Model> clazz);

}