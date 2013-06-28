package org.vplus.core.tmp;

import java.util.List;

import org.hibernate.Session;

public class RepositoryImpl<M> implements Repository {
	
	private Session session;
	
	private RepositoryImpl(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Model> find() {
		return session
				.createCriteria(Model.class)
				.list();
	}

	public static Repository create(Session session) {
		return new RepositoryImpl<Model>(session);
	}

	@Override
	public Session session() {
		return session;
	}
}
