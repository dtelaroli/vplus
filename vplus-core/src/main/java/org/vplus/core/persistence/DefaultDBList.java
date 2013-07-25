package org.vplus.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.util.ClassUtil;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBList implements DBList {

	private final EntityManager em;
	private ClassUtil classUtil;

	public DefaultDBList(EntityManager em) {
		this.em = em;
		this.classUtil = ClassUtil.create();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Model> List<T> find() throws VPlusException {
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery(classUtil.getClazz());
	    Root<T> entityRoot = (Root<T>) criteria.from(classUtil.getClazz());
	    criteria.select(entityRoot);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public DBList of(Class<? extends Model> clazz) {
		classUtil = classUtil.from(clazz);
		return this;
	}

}