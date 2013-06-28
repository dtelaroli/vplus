package org.vplus.core.tmp;

import java.util.List;

import org.hibernate.Session;

public interface Repository {

	List<Model> find();

	Session session();

}
