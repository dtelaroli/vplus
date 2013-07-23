package org.vplus.core.jpa;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.Component;

import com.google.common.base.Strings;

@Component
public class JPAUtil {
	
	private Logger LOG = LoggerFactory.getLogger(JPAUtil.class.getName());

	private EntityManager em;
	protected String unit;
	
	public JPAUtil() {}

	public JPAUtil(String unit) {
		this.unit = unit;
	}

	public JPAUtil withUnit(String unit) {
		LOG.debug("Persistence unit name: {}", unit);
		this.unit = unit;
		return this;
	}

	public EntityManager entityManager() {
		if(Strings.isNullOrEmpty(unit)) {
			String message = "Unit name not defined. Execute withUnit(String)";
			throw new PersistenceException(message);
		}
		if(em == null) {
			LOG.debug("Open EntityManager");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(unit);
			em = emf.createEntityManager();
		}
		return em;
	}

	public Connection connection() {
		LOG.debug("Opening Connection");
		return getSessionImpl().connection();
	}
	
	private SessionImpl getSessionImpl() {
		return (SessionImpl) entityManager().getDelegate();
	}

	public void destroy() {
		if(em != null) {
			LOG.debug("Close EntityManager");
			em.close();
		}
	}
	
}