package org.vplus.core.generics;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
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
		setStatus(Status.INACTIVE);
	}

	public void active() {
		setStatus(Status.ACTIVE);		
	}

	public void removed() {
		setStatus(Status.REMOVED);
	}
	
	protected void setStatus(Status status) {
		filter = hibernateSession().enableFilter(STATUS_FILTER);
		filter.setParameter(STATUS_PARAM, status.ordinal());
	}

	protected Session hibernateSession() {
		return (SessionImpl) em.getDelegate();		
	}
	
	protected Filter filter() {
		return filter;
	}

}