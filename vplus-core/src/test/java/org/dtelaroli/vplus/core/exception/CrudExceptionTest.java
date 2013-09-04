package org.dtelaroli.vplus.core.exception;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CrudExceptionTest {

	private static final String TEST = "test";
	CrudException ex;
	
	@Before
	public void setUp() throws Exception {
		ex = new CrudException(TEST);
	}

	@Test
	public void shouldReturnTestMessage() {
		assertThat(ex.getMessage(), equalTo(TEST));
	}

}
