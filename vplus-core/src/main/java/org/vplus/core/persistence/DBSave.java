package org.vplus.core.persistence;

import org.vplus.core.generics.Model;

public interface DBSave extends DAO {

	Class<? extends Model> type();

	<T extends Model> T persist(T model);
	
	DBSave of(Class<? extends Model> clazz);

}