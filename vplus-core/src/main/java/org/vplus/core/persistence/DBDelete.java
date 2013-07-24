package org.vplus.core.persistence;

import org.vplus.core.generics.Model;

public interface DBDelete extends DAO {

	<T extends Model> void delete(T model);
	
	DBDelete of(Class<? extends Model> clazz);

}