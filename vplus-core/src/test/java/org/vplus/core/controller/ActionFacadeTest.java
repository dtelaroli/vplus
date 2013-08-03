package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.generics.StatusFilter;
import org.vplus.core.persistence.Persistence;
import org.vplus.core.util.TypeUtil;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

public class ActionFacadeTest {

	ActionFacade facade;
	@Mock private Persistence persistence;
	@Mock private Result result;
	@Mock private Validator validator;
	@Mock TypeUtil typeUtil;
	@Mock StatusFilter filter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		facade = new ActionFacadeImpl(persistence, result, validator, typeUtil, filter);
	}

	@Test
	public void shouldReturnPersistence() {
		assertThat(facade.persistence(), equalTo(persistence));
	}
	
	@Test
	public void shouldReturnResult() {
		assertThat(facade.result(), equalTo(result));
	}
	
	@Test
	public void shouldReturnValidator() {
		assertThat(facade.validator(), equalTo(validator));
	}
	
	@Test
	public void shouldReturnTypeUtil() {
		assertThat(facade.typeUtil(), equalTo(typeUtil));
	}

}
