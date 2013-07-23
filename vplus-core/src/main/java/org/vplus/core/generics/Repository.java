package org.vplus.core.generics;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public abstract class Repository<T extends Model> {
	
	protected final EntityManager entityManager;
	protected final Class<T> clazz;

	protected Repository(EntityManager entityManager) {
		this.entityManager = entityManager;
		
		Class<T> clazz = getClassType();

		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	public void create(T entity) {
		entityManager.persist(entity);
	}
	
	public T update(T entity) {
		return entityManager.merge(entity);
	}
	
	public void destroy(T entity) {
		entityManager.remove(entity);
	}
	
	public T find(Long id) {
		return entityManager.find(clazz, id);
	}
	
	public List<T> find() {
		CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
		TypedQuery<T> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}
}