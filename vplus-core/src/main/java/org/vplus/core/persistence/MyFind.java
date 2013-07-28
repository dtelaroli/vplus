package org.vplus.core.persistence;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.generics.MyEntity;

public class MyFind implements Dao {
	
	private EntityManager em;

	public MyFind(EntityManager em) {
		this.em = em;
	}
	
	public MyEntity find() {
		CriteriaBuilder b = em.getCriteriaBuilder();
		CriteriaQuery<MyEntity> q = b.createQuery(MyEntity.class);
	    Root<MyEntity> root = q.from(MyEntity.class);
		return em.createQuery(q.select(root))
				.setMaxResults(1)
				.getSingleResult();
	}
	
}