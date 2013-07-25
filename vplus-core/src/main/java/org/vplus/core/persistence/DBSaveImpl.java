package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBSaveImpl implements DBSave {

	private EntityManager em;

	public DBSaveImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public <T extends Model> T persist(T model) {
		return em.merge(model);
	}

}