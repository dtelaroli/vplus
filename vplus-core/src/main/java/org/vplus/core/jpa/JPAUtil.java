package org.vplus.core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class JPAUtil {
	
	private Logger LOG = LoggerFactory.getLogger(JPAUtil.class.getName());

	private EntityManager em;
	protected String unit;

	protected JPAUtil withUnit(String unit) {
		LOG.debug("Persistence unit name: {}", unit);
		this.unit = unit;
		return this;
	}

	public EntityManager build() {
		if(Strings.isNullOrEmpty(unit)) {
			String message = "Unit name not defined. Execute withUnit(String)";
			throw new PersistenceException(message);
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(unit);
		em = emf.createEntityManager();
		return em;
	}
	
}