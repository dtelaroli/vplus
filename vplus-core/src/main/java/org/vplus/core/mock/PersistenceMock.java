package org.vplus.core.mock;

import org.vplus.core.persistence.DBList;
import org.vplus.core.persistence.Dao;
import org.vplus.core.persistence.Persistence;

public class PersistenceMock implements Persistence {

	@Override
	public <T extends Dao> T use(Class<T> dao) {
		if(dao.isAssignableFrom(DBList.class)) {
			return new DBList(em);
		}
		
		return null;
	}

}
