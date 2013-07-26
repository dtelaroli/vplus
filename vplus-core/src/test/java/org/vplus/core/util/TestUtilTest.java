package org.vplus.core.util;

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
		assertThat(util.getEnv(), notNullValue());
		assertThat(util.getJpaUtil(), notNullValue());
		assertThat(util.getDbUnitUtil(), notNullValue());
		assertThat(util.getActionFacadeMock(), notNullValue());
		assertThat(util.getResultMock(), notNullValue());
		assertThat(util.getTypeUtil(), notNullValue());
	}

}