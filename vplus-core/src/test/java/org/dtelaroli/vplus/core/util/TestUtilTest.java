package org.dtelaroli.vplus.core.util;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TestUtilTest {

	TestUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = TestUtil.create();
	}

	@Test
	public void shouldInitialize() {
		assertThat(util.jpaUtil(), notNullValue());
		assertThat(util.dbUnitUtil(), notNullValue());
		assertThat(util.actionFacadeMock(), notNullValue());
		assertThat(util.resultMock(), notNullValue());
		assertThat(util.typeUtil(), notNullValue());
	}

}