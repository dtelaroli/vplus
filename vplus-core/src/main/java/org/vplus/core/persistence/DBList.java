package org.vplus.core.persistence;

import java.util.List;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

public interface DBList extends DAO {

	public <T extends Model> List<T> find() throws VPlusException;

	DBList of(Class<? extends Model> clazz);

}