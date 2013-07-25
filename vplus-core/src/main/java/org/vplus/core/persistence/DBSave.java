package org.vplus.core.persistence;

import org.vplus.core.generics.Model;

public interface DBSave extends Dao {

	<T extends Model> T persist(T model);
	
}