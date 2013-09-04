package org.dtelaroli.vplus.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.model.Model;

import br.com.caelum.vraptor.ioc.Component;

import com.google.common.base.Strings;

@Component
public class DBList implements Dao {

	protected static final int DEFAULT_LIMIT = 100;
	private Class<? extends Model> clazz;
	private EntityManager em;
	private String order;
	private Direction direction;
	private int limit;
	
	public DBList(EntityManager em) {
		this.em = em;
	}

	public DBList of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<Model> find() throws CrudException {
		CriteriaBuilder b = em.getCriteriaBuilder();
		CriteriaQuery<Model> q = (CriteriaQuery<Model>) b.createQuery(clazz);
	    Root<Model> root = (Root<Model>) q.from(clazz);
	    q.select(root);
	    if(!Strings.isNullOrEmpty(order)) {
			q.orderBy(makeOrder(b, root));
		}
		return em.createQuery(q).setMaxResults(limit).getResultList();
	}

	private Order makeOrder(CriteriaBuilder b,
			Root<Model> root) throws CrudException {
		return direction.makeOrder(b, root, order());
	}

	public DBList withOrder(String order) {
		this.order = order;
		return this;
	}

	public String order() {
		return order;
	}
	
	public DBList withDirection(Direction order) {
		direction = order;
		return this;
	}

	public DBList withLimit(Integer limit) {
		this.limit = prepareLimit(limit);
		return this;
	}

	private int prepareLimit(Integer limit) {
		return limit == null ? DEFAULT_LIMIT : limit;
	}

	public Integer limit() {
		return limit;
	}

}