package org.dtelaroli.vplus.core.util;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.dbunit.DatabaseUnitException;
import org.dtelaroli.vplus.core.mock.ActionFacadeMock;
import org.dtelaroli.vplus.core.model.Model;

import br.com.caelum.vraptor.environment.DefaultEnvironment;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.util.test.MockSerializationResult;

public class TestUtil {
	
	protected JPAUtil jpaUtil;
	protected DBUnitUtil dbUnitUtil;
	protected Environment env;
	private ActionFacadeMock actionFacadeMock;
	
	TestUtil() throws IOException {
		jpaUtil = new JPAUtil("test");
		env = new DefaultEnvironment("vplus");
		dbUnitUtil = new DBUnitUtil(jpaUtil, env);
		actionFacadeMock = new ActionFacadeMock();
	}
	
	public static TestUtil create() throws IOException {
		return new TestUtil();
	}

	public EntityManager entityManager() {
		return jpaUtil.entityManager();
	}

	public void beginTransaction() {
		jpaUtil.beginTransaction();
	}

	public void commit() {
		jpaUtil.commit();
	}

	public DBUnitUtil from(Class<? extends Model>... clazz) {
		return dbUnitUtil.from(clazz);
	}

	public void cleanAndDestroy() throws DatabaseUnitException {
		dbUnitUtil.cleanAndClose();
	}
	
	public JPAUtil jpaUtil() {
		return jpaUtil;
	}
	
	public DBUnitUtil dbUnitUtil() {
		return dbUnitUtil;
	}
	
	public Environment environment() {
		return env;
	}

	public ActionFacadeMock actionFacadeMock() {
		return actionFacadeMock;
	}

	public MockSerializationResult resultMock() {
		return actionFacadeMock.result();
	}
	
	public TypeUtil typeUtil() {
		return actionFacadeMock.typeUtil();
	}
}
