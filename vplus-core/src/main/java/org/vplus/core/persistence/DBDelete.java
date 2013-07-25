package org.vplus.core.persistence;

import org.vplus.core.generics.Model;

public interface DBDelete extends Dao {

	<T extends Model> void delete(T model);
	
}