package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBLoad implements DBLoad {

	private EntityManager em;
	private Class<? extends Model> clazz;

	public DefaultDBLoad(EntityManager em) {
		this.em = em;
	}

	/* (non-Javadoc)
	 * @see org.vplus.core.crud.DBLoad#find(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Model> T find(Long id) {
		if(clazz == null) {
			throw new IllegalArgumentException("Entity is null. Execute the of(Class<? extends Model> clazz) method.");
		}
		return (T) em.find(clazz, id);
	}

	@Override
	public DBLoad of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

}