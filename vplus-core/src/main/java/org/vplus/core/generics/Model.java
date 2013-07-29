package org.vplus.core.generics;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Model implements Serializable {
	
	private static final long serialVersionUID = -3870444760824221901L;
	
	@Id @GeneratedValue
	protected Long id;
	
	public Model() {
	}

	public Model(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getLabel() throws Exception {
		//TODO teste
		Field field = getClass().getDeclaredField(orderField());
		return (String) field.get(new String());
	}
	
	public String[] includes() {
		return new String[]{};
	}
	
	public String[] excludes() {
		return new String[]{};
	}
	
	public String orderField() {
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Model other = (Model) obj;
		if (id != other.id && (id == null || !id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + "]";
	}
	
}