package org.vplus.core.persistence;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

public interface DBLoad extends DAO {

	<T extends Model> T find(Long id) throws VPlusException;

	DBLoad of(Class<? extends Model> clazz);

}