package org.vplus.core.tmp;

import java.util.List;

public class DefaultListStrategy<DAO> implements ListStrategy<DAO> {

	private Service<DAO> service;
	
	public DefaultListStrategy(Service<DAO> service) {
		this.service = service;
	}
	
	@Override
	public List<Model> find() {
		return service.find();
	}

}
