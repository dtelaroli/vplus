package org.vplus.core.crud;

import org.vplus.core.generics.Model;

public interface DBDelete extends DAO {

	Class<? extends Model> type();

	<T extends Model> void delete(T model);
	
	DBDelete of(Class<? extends Model> clazz);

}