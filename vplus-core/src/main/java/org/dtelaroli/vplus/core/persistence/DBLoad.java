package org.dtelaroli.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.model.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBLoad implements Dao {

	private EntityManager em;

	public DBLoad(EntityManager em) {
		this.em = em;
	}

	public Model find(Model model) throws CrudException {
		return em.find(model.getClass(), model.getId());
	}

}