package org.vplus.core.tmp;

import java.util.List;

public class ServiceImpl implements Service<Repository> {

	private Repository repository;
	
	public ServiceImpl(Repository repository) {
		this.repository = repository;
	}

	@Override
	public List<Model> find() {
		return repository.find();
	}

}