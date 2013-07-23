package org.vplus.core.crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.generics.Model;

public class DefaultList implements DAO {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DefaultList(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find() {
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery(clazz);
	    Root<T> entityRoot = (Root<T>) criteria.from(clazz);
	    criteria.select(entityRoot);
		return em.createQuery(criteria).getResultList();
	}

	public DefaultList of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	public Class<?> type() {
		return clazz;
	}

}