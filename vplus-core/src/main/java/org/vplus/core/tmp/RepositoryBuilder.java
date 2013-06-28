package org.vplus.core.tmp;

public class RepositoryBuilder {
	
	private Repository repository;
	
	private RepositoryBuilder(Repository repository) {
		this.repository = repository;
	}

	public static RepositoryBuilder create(Repository repository) {
		return new RepositoryBuilder(repository);
	}

	public Repository build() {
		return repository;
	}
}
