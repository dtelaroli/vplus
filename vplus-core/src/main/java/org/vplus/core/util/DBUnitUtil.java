package org.vplus.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.environment.Environment;

import com.google.common.base.Strings;

public class DBUnitUtil {
	
	private Logger LOG = LoggerFactory.getLogger(DBUnitUtil.class.getName());

	protected static final String VPLUS_DBUNIT_DATASET_PACKAGE = "vplus.dbunit.datasetPackage";
	protected static final String DEFAULT_DATASET_PATH = "src/test/java/datasets";
	private final JPAUtil jpa;
	private final Environment env;
	private List<String> datasetNames;
	private List<IDataSet> datasets;

	public DBUnitUtil(JPAUtil jpa, Environment env) {
		this.jpa = jpa;
		this.env = env;
		this.datasetNames = new ArrayList<String>();
		this.datasets = new ArrayList<IDataSet>();
	}

	public DBUnitUtil from(Class<? extends Model>... classes) {
		for (Class<? extends Model> c : classes) {
			String name = c.getSimpleName();
			datasetNames.add(name);
			LOG.debug("Setting dataset {}", name);
		}
		return this;
	}

	protected List<String> datasetNames() {
		return datasetNames;
	}

	public DBUnitUtil init() throws Exception {
		validateDataSet();
		initDatasets();
		try {
			for (IDataSet dataset : datasets) {
				DatabaseOperation.CLEAN_INSERT.execute(connection(), dataset);
			}
		} catch (Exception e) {
			throw new DatabaseUnitException(e);
		}
		return this;
	}

	private void validateDataSet() throws DatabaseUnitException {
		if(datasetNames.isEmpty()) {
			DatabaseUnitException exception = new DatabaseUnitException();
			LOG.error("Dataset names is undefined. You should execute from(YourObject.class)", exception);
			throw exception;
		}
	}

	protected IDatabaseConnection connection() throws DatabaseUnitException {
		return new DatabaseConnection(jpa.connection());
	}

	public void close() {
		jpa.close();
	}
	
	public void clean() throws DatabaseUnitException {
		validateDataSet();
		try {
			for (IDataSet dataSet : datasets) {
				DatabaseOperation.DELETE_ALL.execute(connection(), dataSet);
			}
		} catch (Exception e) {
			throw new DatabaseUnitException(e);
		}
	}
	
	public void cleanAndClose() throws DatabaseUnitException {
		clean();
		close();
	}

	protected String defaultDatasetPath() {
		String path = env.get(VPLUS_DBUNIT_DATASET_PACKAGE);
		if(!Strings.isNullOrEmpty(path)) {
			LOG.debug("Using dataset path {}", path);
			return path;
		}
		LOG.info("Using default dataset path {}", DEFAULT_DATASET_PATH);
		return DEFAULT_DATASET_PATH;
	}

	protected List<InputStream> datasetXMLs() throws FileNotFoundException {
		List<InputStream> list = new ArrayList<InputStream>();
		for (String name : datasetNames) {
			String dataSetName = getDataSetName(name);
			LOG.debug("Dataset file name: {}", dataSetName);
			list.add(new FileInputStream(dataSetName));
		}
		return list;
	}

	private String getDataSetName(String name) {
		return String.format(
				"%s/%s.xml", defaultDatasetPath(), name
		);
	}

	protected void initDatasets() throws DataSetException, IOException {
		datasets = new ArrayList<IDataSet>();
		for(InputStream is : datasetXMLs()) {
			FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSetBuilder().build(is);
			is.close();
			datasets.add(flatXmlDataSet);
		}
	}
	
	protected List<IDataSet> datasets() {
		return datasets;
	}

}