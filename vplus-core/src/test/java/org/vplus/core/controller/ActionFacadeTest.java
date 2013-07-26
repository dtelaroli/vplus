package org.vplus.core.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

public class ActionFacadeTest {

	ActionFacade facade;
	@Mock private Persistence persistence;
	@Mock private Result result;
	@Mock private Validator validator;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		facade = new ActionFacade(persistence, result, validator);
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

}
