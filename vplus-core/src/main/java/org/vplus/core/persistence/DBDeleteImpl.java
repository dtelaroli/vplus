package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBDeleteImpl implements DBDelete {

	private EntityManager em;

	public DBDeleteImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public <T extends Model> void delete(T model) {
		em.remove(em.merge(model));
	}

}