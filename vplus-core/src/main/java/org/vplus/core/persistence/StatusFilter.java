package org.vplus.core.persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.vplus.core.generics.Status;

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

	public void enableFilter() {
		getFilter();
		setParameter(Status.ACTIVE);
	}

	protected void setParameter(Status status) {
		filter.setParameter(STATUS_PARAM, status.ordinal());
	}

	private void getFilter() {
		filter = hibernateSession().enableFilter(STATUS_FILTER);
	}

	protected Session hibernateSession() {
		return (SessionImpl) em.getDelegate();		
	}
	
	protected Filter filter() {
		return filter;
	}

	public void disableFilter() {
		hibernateSession().disableFilter(STATUS_FILTER);
		filter = null;
	}

	public boolean isActive() {
		return filter != null;
	}

	public void setInactive() {
		getFilter();
		setParameter(Status.INACTIVE);
	}

	public void setActive() {
		getFilter();
		setParameter(Status.ACTIVE);		
	}

	public void setRemoved() {
		getFilter();
		setParameter(Status.REMOVED);
	}
}