package org.vplus.core.tmp;

import java.util.List;

import br.com.caelum.vraptor.Result;

public interface Controller {

	String RESULT_VAR_LIST = "resultList";

	List<Model> index();
	
	Result getResult();

	void setResult(Result result);

	void setService(Service<Repository> service);

	Service<Repository> getService();

}
