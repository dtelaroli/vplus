package org.dtelaroli.vplus.core.mock;

import static org.mockito.Mockito.doReturn;

import org.dtelaroli.vplus.core.controller.ActionFacade;
import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.model.StatusFilter;
import org.dtelaroli.vplus.core.persistence.Dao;
import org.dtelaroli.vplus.core.persistence.Persistence;
import org.dtelaroli.vplus.core.util.TypeUtil;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class ActionFacadeMock implements ActionFacade {

	@Mock private Persistence persistence;
	private MockSerializationResult mockSerializationResult;
	private JSR303MockValidator jsr303MockValidator;
	private TypeUtil typeUtil;
    @Mock private StatusFilter filter;

	public ActionFacadeMock() {
		MockitoAnnotations.initMocks(this);
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

	@Override
	public void setFilter(Status status) {
		
	}
}