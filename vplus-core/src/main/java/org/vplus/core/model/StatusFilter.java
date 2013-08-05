package org.vplus.core.model;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class StatusFilter implements Serializable {
	
	private static final long serialVersionUID = 3988997348937148149L;
	
	private static final String STATUS_PARAM = "status";
	protected static final String STATUS_FILTER = "statusFilter";
	private EntityManager em;
	private Filter filter;

	public StatusFilter(EntityManager em) {
		this.em = em;
	}

	public void disableFilter() {
		hibernateSession().disableFilter(STATUS_FILTER);
		filter = null;
	}

	public boolean isActiveFilter() {
		return filter != null;
	}

	public void inactive() {
		setStatus(Status.Inactive);
	}

	public void active() {
		setStatus(Status.Active);		
	}

	public void removed() {
		setStatus(Status.Removed);
	}
	
	public void setStatus(Status status) {
		if(status != null) {
			filter = hibernateSession().enableFilter(STATUS_FILTER);
			filter.setParameter(STATUS_PARAM, status.ordinal());
		}
	}

	protected Session hibernateSession() {
		return (SessionImpl) em.getDelegate();		
	}
	
	protected Filter filter() {
		return filter;
	}

}