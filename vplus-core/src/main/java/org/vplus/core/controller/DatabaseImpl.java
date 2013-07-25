package org.vplus.core.controller;

import static org.vplus.core.persistence.Persistences.list;
import static org.vplus.core.persistence.Persistences.load;

import java.util.List;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DatabaseImpl implements Database {

	private final Persistence persistence;
	private final View view;
	private Class<? extends Model> clazz;

	public DatabaseImpl(Persistence persistence, View view) {
		this.persistence = persistence;
		this.view = view;
	}

	@Override
	public Database of(Class<? extends Model> clazz) {
		this.clazz = clazz;
		return this;
	}

	@Override
	public View find() throws VPlusException {
		List<Model> list = persistence.use(list()).of(clazz).find();
		return view.from(list);
	}
	
	@Override
	public View find(long id) throws VPlusException {
		return view.from(persistence.use(load()).of(clazz).find(id));
	}
	
}