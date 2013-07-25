package org.vplus.core.persistence;

import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DBListImpl.DBListExecute;

public interface DBList extends Dao {

	DBListExecute of(Class<? extends Model> clazz);

}