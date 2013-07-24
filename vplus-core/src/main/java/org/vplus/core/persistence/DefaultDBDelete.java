package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBDelete implements DBDelete {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DefaultDBDelete(EntityManager em) {
		this.em = em;
	}

	@Override
	public DBDelete of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return clazz;
	}

	@Override
	public <T extends Model> void delete(T model) {
		if(clazz == null) {
			throw new IllegalArgumentException("Entity is null. Execute the of(Class<? extends Model> clazz) method.");
		}
		em.remove(em.merge(model));
	}

}