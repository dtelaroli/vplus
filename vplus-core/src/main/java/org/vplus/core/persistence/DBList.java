package org.vplus.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBList implements Dao {

	private Class<? extends Model> clazz;
	private EntityManager em;
	
	public DBList(EntityManager em) {
		this.em = em;
	}

	public DBList of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<Model> find() throws VPlusException {
		CriteriaQuery<Model> criteria = (CriteriaQuery<Model>) em.getCriteriaBuilder().createQuery(clazz);
	    Root<Model> entityRoot = (Root<Model>) criteria.from(clazz);
	    criteria.select(entityRoot);
		return em.createQuery(criteria).getResultList();
	}

}