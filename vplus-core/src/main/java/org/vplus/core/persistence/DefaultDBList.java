package org.vplus.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBList implements DBList {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DefaultDBList(EntityManager em) {
		this.em = em;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Model> List<T> find() {
		if(clazz == null) {
			throw new IllegalArgumentException("Entity is null. Execute the of(Class<? extends Model> clazz) method.");
		}
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery(clazz);
	    Root<T> entityRoot = (Root<T>) criteria.from(clazz);
	    criteria.select(entityRoot);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public DBList of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public Class<? extends Model> type() {
		return clazz;
	}

}