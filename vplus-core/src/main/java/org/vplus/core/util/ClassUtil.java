package org.vplus.core.util;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

public class ClassUtil {
	
	private Class<? extends Model> clazz;
	
	public ClassUtil() {
	
	}

	public static ClassUtil create() {
		return new ClassUtil();
	}

	public ClassUtil from(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}
	
	public Class<?> getClazz() throws VPlusException {
		if(clazz == null) {
			throw new VPlusException("Entity is null. Execute the of(Class<? extends Model> clazz) method.");
		}
		return clazz;
	}
}