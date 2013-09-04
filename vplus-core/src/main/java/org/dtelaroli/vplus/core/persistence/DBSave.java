package org.dtelaroli.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.dtelaroli.vplus.core.model.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBSave implements Dao {

	private EntityManager em;

	public DBSave(EntityManager em) {
		this.em = em;
	}

	public Model persist(Model model) {
		model = em.merge(model);
		em.flush();
		return model;
	}
}