package org.dtelaroli.vplus.core.util;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.MyEntity;
import org.dtelaroli.vplus.core.model.NewEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	@SuppressWarnings("unchecked")
	@Test
	public void shouldConfigureDatasetName() throws DataSetException, IOException {
		dbunit.from(MyEntity.class);
		assertThat(dbunit.datasetNames(), not(empty()));
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetDatasetXml() throws Exception {
		List<InputStream> is = dbunit.from(MyEntity.class).datasetXMLs();
		assertThat(is, not(empty()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCreateFlatDataset() throws Exception {
		dbunit.from(MyEntity.class, NewEntity.class).initDatasets();
		assertThat(dbunit.datasets().size(), equalTo(2));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = FileNotFoundException.class)
	public void shouldDispatchErrorIfNotFound() throws Exception {
		dbunit.from(Model.class).initDatasets();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldInsertItemsInDatabase() throws Exception {
		dbunit.from(MyEntity.class).init();
	}
	
	@Test(expected = DatabaseUnitException.class)
	public void shouldDispatchErrorIfDatasetNameNotConfigOnInit() throws Exception {
		dbunit.init();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCleanDatabase() throws Exception {
		dbunit.from(MyEntity.class).clean();
	}
	
	@Test(expected = DatabaseUnitException.class)
	public void shouldDispatchErrorIfDatasetNameNotConfigOnClean() throws Exception {
		dbunit.clean();
	}
	
}