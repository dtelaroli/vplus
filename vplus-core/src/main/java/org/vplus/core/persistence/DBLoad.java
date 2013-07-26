package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBLoad implements Dao {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DBLoad(EntityManager em) {
		this.em = em;
	}

	public Model find(Model model) throws VPlusException {
		return em.find(clazz, model.getId());
	}

	public DBLoad of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

}