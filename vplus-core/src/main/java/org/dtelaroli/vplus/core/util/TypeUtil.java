package org.dtelaroli.vplus.core.util;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class TypeUtil {

	private Class<?> clazz;

	public TypeUtil() {
	}

	public TypeUtil of(Class<?> clazz) {
		this.clazz = clazz;
		return this;
	}

	public boolean compare(Class<?> other) {
		if(other == null) {
			return false;
		}
		else if(clazz.isAssignableFrom(other)) {
			return true;
		}
		return compare(other.getSuperclass());
	}

	public boolean compare(List<?> list) {
		if(list.isEmpty()) {
			return false;
		}
		return compare(list.get(0).getClass());
	}

	public boolean isListFrom(Object object) {
		try {
			List<?> list = (List<?>)object;
			return compare(list);
		}
		catch(Exception e) {
		}
		return false;
	}

}
