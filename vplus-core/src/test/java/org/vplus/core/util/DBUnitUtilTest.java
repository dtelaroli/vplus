package org.vplus.core.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vplus.core.generics.MyEntity;

import br.com.caelum.vraptor.environment.DefaultEnvironment;
import br.com.caelum.vraptor.environment.Environment;

public class DBUnitUtilTest {

	DBUnitUtil dbunit;
	private Environment env;
	private JPAUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = new JPAUtil("test");
		env = spy(new DefaultEnvironment("vplus"));
		dbunit = new DBUnitUtil(util, env);
	}
	
	@After
	public void tearDown() {
		dbunit.close();
	}

	@Test
	public void shouldConfigureDatasetName() {
		dbunit.from(MyEntity.class);
		assertThat(dbunit.datasetName(), equalTo("MyEntity"));
	}
	
	@Test
	public void shouldCreateConnection() throws DatabaseUnitException, SQLException {
		IDatabaseConnection conn = dbunit.connection();
		assertThat(conn.getConnection().isClosed(), equalTo(false));		
	}
	
	@Test
	public void shouldGetResourceDefaultPath() {
		String path = dbunit.defaultDatasetPath();
		assertThat(path, equalTo(DBUnitUtil.DEFAULT_DATASET_PATH));
	}
	
	@Test
	public void shouldGetResourceDefaultPathWhenEnvIsNull() {
		when(env.get(DBUnitUtil.VPLUS_DBUNIT_DATASET_PACKAGE)).thenReturn(null);
		String path = dbunit.defaultDatasetPath();
		assertThat(path, equalTo(DBUnitUtil.DEFAULT_DATASET_PATH));
	}
	
	@Test
	public void shouldGetDatasetXml() throws FileNotFoundException {
		InputStream is = dbunit.from(MyEntity.class).datasetXML();
		assertThat(is, notNullValue());
	}
	
	@Test
	public void shouldCreateFlatDataset() throws DataSetException, IOException {
		IDataSet ds = dbunit.from(MyEntity.class).createDataset();
		assertThat(ds, notNullValue());
	}
	
	@Test
	public void shouldInsertItemsInDatabase() throws DatabaseUnitException {
		dbunit.from(MyEntity.class).init();
	}
	
	@Test(expected = DatabaseUnitException.class)
	public void shouldDispatchErrorIfDatasetNameNotConfigOnInit() throws DatabaseUnitException {
		dbunit.init();
	}
	
	@Test
	public void shouldCleanDatabase() throws DatabaseUnitException {
		dbunit.from(MyEntity.class).clean();
	}
	
	@Test(expected = DatabaseUnitException.class)
	public void shouldDispatchErrorIfDatasetNameNotConfigOnClean() throws DatabaseUnitException {
		dbunit.clean();
	}
	
}