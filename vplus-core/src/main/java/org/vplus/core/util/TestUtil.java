package org.vplus.core.util;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.dbunit.DatabaseUnitException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.environment.DefaultEnvironment;
import br.com.caelum.vraptor.environment.Environment;

public class TestUtil {
	
	protected JPAUtil jpaUtil;
	protected DBUnitUtil dbUnitUtil;
	protected Environment env;
	
	TestUtil() throws IOException {
		jpaUtil = new JPAUtil("test");
		env = new DefaultEnvironment("vplus");
		dbUnitUtil = new DBUnitUtil(jpaUtil, env);
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

	public DBUnitUtil from(Class<? extends Model> clazz) {
		return dbUnitUtil.from(clazz);
	}

	public void cleanAndDestroy() throws DatabaseUnitException {
		dbUnitUtil.cleanAndDestroy();
	}
	
	public JPAUtil getJpaUtil() {
		return jpaUtil;
	}
	
	public DBUnitUtil getDbUnitUtil() {
		return dbUnitUtil;
	}
	
	public Environment getEnv() {
		return env;
	}
	
}
