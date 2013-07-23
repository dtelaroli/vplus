package org.vplus.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeGenericUtil {

	public static TypeGenericUtil create() {
		return new TypeGenericUtil();
	}

	public <T> Class<?> from(Class<T> clazz) {
		ParameterizedType parameterizedType = getGenericSuperclass(clazz);
		return (Class<?>) getActualTypeArguments(parameterizedType);
	}

	private Type getActualTypeArguments(ParameterizedType parameterizedType) {
		return parameterizedType.getActualTypeArguments()[0];
	}

	private <T> ParameterizedType getGenericSuperclass(Class<T> clazz) {
		return (ParameterizedType) clazz.getGenericSuperclass();
	}

}
