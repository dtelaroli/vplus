package org.vplus.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBListImpl implements DBList {

	private DBListExecute listExecute;

	public DBListImpl(DBListExecute listExecute) {
		this.listExecute = listExecute;
	}

	@Override
	public DBListExecute of(Class<? extends Model> clazz) {
		return listExecute.of(clazz);
	}
	
	@Component
	public static class DBListExecute {
		private final EntityManager em;
		private Class<? extends Model> clazz;
		
		public DBListExecute(EntityManager em) {
			this.em = em;
		}
		
		private DBListExecute of(Class<? extends Model> clazz) {
			this.clazz = clazz;
			return this;
		}

		@SuppressWarnings("unchecked")
		public <T extends Model> List<T> find() throws VPlusException {
			CriteriaQuery<T> criteria = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery(clazz);
		    Root<T> entityRoot = (Root<T>) criteria.from(clazz);
		    criteria.select(entityRoot);
			return em.createQuery(criteria).getResultList();
		}
	}

}