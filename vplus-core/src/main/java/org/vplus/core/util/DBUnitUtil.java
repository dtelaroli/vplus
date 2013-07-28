package org.vplus.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
	private String dataset;

	public DBUnitUtil(JPAUtil jpa, Environment env) {
		this.jpa = jpa;
		this.env = env;
	}

	public DBUnitUtil from(Class<? extends Model> clazz) {
		dataset = clazz.getSimpleName();
		LOG.debug("Setting dataset {}", dataset);
		return this;
	}

	protected String datasetName() {
		return dataset;
	}

	public DBUnitUtil init() throws DatabaseUnitException {
		validateDataSet();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection(), createDataset());
		} catch (Exception e) {
			throw new DatabaseUnitException(e);
		}
		return this;
	}

	private void validateDataSet() {
		if(Strings.isNullOrEmpty(dataset)) {
			LOG.error("Dataset name is undefined. You should execute from(YourObject.class)", new DatabaseUnitException());
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
			DatabaseOperation.DELETE_ALL.execute(connection(), createDataset());
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

	protected InputStream datasetXML() throws FileNotFoundException {
		String dataSetName = getDataSetName();
		LOG.debug("Dataset file name: {}", dataSetName);
		return new FileInputStream(dataSetName);
	}

	private String getDataSetName() {
		return String.format(
				"%s/%s.xml", defaultDatasetPath(), dataset
		);
	}

	protected IDataSet createDataset() throws DataSetException, IOException {
		InputStream datasetXML = datasetXML();
		FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSetBuilder().build(datasetXML);
		datasetXML.close();
		return flatXmlDataSet;
	}

}