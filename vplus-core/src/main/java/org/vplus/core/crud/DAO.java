package org.vplus.core.crud;

import org.vplus.core.generics.Model;

public interface DAO {

	DefaultList of(Class<? extends Model> clazz);

}
