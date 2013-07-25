package org.vplus.core.persistence;

import org.vplus.core.generics.Model;
import org.vplus.core.persistence.DefaultDBList.DBListExecute;

public interface DBList extends DAO {

	DBListExecute of(Class<? extends Model> clazz);

}