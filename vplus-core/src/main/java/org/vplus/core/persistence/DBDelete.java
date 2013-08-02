package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBDelete implements Dao {

	private EntityManager em;

	public DBDelete(EntityManager em) {
		this.em = em;
	}

	public void delete(Model model) {
		em.remove(getOld(model));
	}

	private Model getOld(Model model) {
		if(model == null) {
			throw new IllegalArgumentException("Model is null");
		}
		return em.find(model.getClass(), model.getId());
	}

}