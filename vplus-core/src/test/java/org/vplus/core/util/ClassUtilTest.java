package org.vplus.core.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.vplus.core.exeption.VPlusException;
import org.vplus.core.persistence.MyEntity;

public class ClassUtilTest {
	
	ClassUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = ClassUtil.create();
	}

	@Test
	public void shouldSetClass() throws VPlusException {
		util = util.from(MyEntity.class);
		assertThat(util.getClazz().isAssignableFrom(MyEntity.class), is(true));
	}
	
	@Test(expected = VPlusException.class)
	public void shouldDispatchExceptionIfClassIsNull() throws VPlusException {
		util.getClazz();
	}
	
}
