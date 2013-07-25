package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DBLoadImpl implements DBLoad {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DBLoadImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Model> T find(Long id) throws VPlusException {
		return (T) em.find(clazz, id);
	}

	@Override
	public DBLoad of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

}