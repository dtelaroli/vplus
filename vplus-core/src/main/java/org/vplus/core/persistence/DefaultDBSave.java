package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBSave implements DBSave {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DefaultDBSave(EntityManager em) {
		this.em = em;
	}

	@Override
	public DefaultDBSave of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public <T extends Model> T persist(T model) {
		if(clazz == null) {
			throw new IllegalArgumentException("Entity is null. Execute the of(Class<? extends Model> clazz) method.");
		}
		return em.merge(model);
	}

}