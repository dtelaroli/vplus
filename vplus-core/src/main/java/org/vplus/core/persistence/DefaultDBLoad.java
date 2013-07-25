package org.vplus.core.persistence;

import javax.persistence.EntityManager;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.util.ClassUtil;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultDBLoad implements DBLoad {

	private EntityManager em;
	private ClassUtil classUtil;

	public DefaultDBLoad(EntityManager em) {
		this.em = em;
		this.classUtil = ClassUtil.create();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Model> T find(Long id) throws VPlusException {
		return (T) em.find(classUtil.getClazz(), id);
	}

	@Override
	public DBLoad of(Class<? extends Model> clazz) {
		classUtil = classUtil.from(clazz);
		return this;
	}

}