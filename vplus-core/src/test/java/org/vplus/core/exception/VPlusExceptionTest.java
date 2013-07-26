package org.vplus.core.exception;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VPlusExceptionTest {

	private static final String TEST = "test";
	VPlusException ex;
	
	@Before
	public void setUp() throws Exception {
		ex = new VPlusException(TEST);
	}

	@Test
	public void shouldReturnTestMessage() {
		assertThat(ex.getMessage(), equalTo(TEST));
	}

}
