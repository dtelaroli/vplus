package org.vplus.core.tmp;

import java.util.List;

import br.com.caelum.vraptor.Result;

public class ControllerImpl<M> implements Controller {

	private Result result;
	private Service<Repository> service;
	
	public ControllerImpl(Service<Repository> service) {
		super();
		this.service = service;
	}
	
	public ControllerImpl<M> build() {
		return this;
	}
	
	@Override
	public List<Model> index() {
		List<Model> list = service.find();
		result.include(RESULT_VAR_LIST, list);
		return list;
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public void setResult(Result result) {
		this.result = result;		
	}

	@Override
	public void setService(Service<Repository> service) {
		this.service = service;
	}

	@Override
	public Service<Repository> getService() {
		return service;
	}

}