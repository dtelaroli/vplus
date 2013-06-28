package org.vplus.core.tmp;

import br.com.caelum.vraptor.Result;

public class ControllerBuilder {

	private Controller controller;
	
	private ControllerBuilder(Controller controller) {
		this.controller = controller;  
	}
	
	public Controller build() {
		return controller;
	}

	public static ControllerBuilder create(Controller controller) {
		return new ControllerBuilder(controller);
	}

	public ControllerBuilder withResult(Result result) {
		controller.setResult(result);
		return this;
	}

	public ControllerBuilder withService(Service<Repository> service) {
		controller.setService(service);
		return this;
	}

}
