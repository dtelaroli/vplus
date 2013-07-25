package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBSave implements Dao {

	private EntityManager em;

	public DBSave(EntityManager em) {
		this.em = em;
	}

	public Model persist(Model model) {
		return em.merge(model);
	}

}