package org.vplus.core.controller;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.view.Results;

@Component
public class ViewImpl implements View {
	
	private Result result;
	private Object object;
	
	protected ViewImpl(Result result) {
		this.result = result;
	}

	@Override
	public View from(Object list) {
		this.object = list;
		return this;
	}

	@Override
	public void json() {
		result.use(Results.json()).from(object).serialize();
	}
}
