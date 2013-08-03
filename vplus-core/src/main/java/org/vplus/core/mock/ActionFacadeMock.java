package org.vplus.core.mock;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.controller.ActionFacade;
import org.vplus.core.generics.StatusFilter;
import org.vplus.core.persistence.Dao;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.persistence.PersistenceImpl;
import org.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionFacadeMock implements ActionFacade {

	private Persistence persistence;
	private MockSerializationResult mockSerializationResult;
	private JSR303MockValidator jsr303MockValidator;
	private TypeUtil typeUtil;
    private StatusFilter filter;

	public ActionFacadeMock() {
		persistence = new PersistenceMock();
		mockSerializationResult = new MockSerializationResult();
		typeUtil = new TypeUtil();
		jsr303MockValidator = new JSR303MockValidator();
	}

	@Override
	public Persistence persistence() {
		return persistence;
	}
	
	@SuppressWarnings("unchecked")
	public ActionFacade withPersistence(Dao dao) {
		Class<? extends Dao> superclass = (Class<? extends Dao>)dao.getClass().getSuperclass();
		doReturn(dao).when(persistence).use(superclass);
		return this;
	}

	@Override
	public MockSerializationResult result() {
		return mockSerializationResult;
	}

	@Override
	public Validator validator() {
		return jsr303MockValidator;
	}

	@Override
	public TypeUtil typeUtil() {
		return typeUtil;
	}

	@Override
	public StatusFilter filter() {
		return filter;
	}
}