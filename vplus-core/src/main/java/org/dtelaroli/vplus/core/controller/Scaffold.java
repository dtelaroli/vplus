package org.dtelaroli.vplus.core.controller;

import net.vidageek.mirror.dsl.Mirror;

import org.dtelaroli.vplus.core.exception.CrudException;
import org.dtelaroli.vplus.core.model.Model;
import org.dtelaroli.vplus.core.model.Status;
import org.dtelaroli.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

public abstract class Scaffold<T extends Model> {

	private Crud crud;

	public Scaffold(Crud controller) {
		this.crud = controller;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> type() {
		return (Class<T>) new Mirror().on(getClass()).reflect().parentGenericType().atPosition(0);
	}

	@Get("")
	public void all() throws CrudException {
		crud.of(type()).list();
	}
	
	@Get("/status/{status}")
	public void all(Status status) throws CrudException {
		crud.of(type()).withStatus(status).list();		
	}
	
	@Get("/order/{order}/dir/{direction}/limit/{limit}")
	public void all(String order, Direction direction, Integer limit) throws CrudException {
		crud.withOrder(order).withDirection(direction).withLimit(limit).list();
	}
	
	@Get("/{model.id}")
	public void get(T model) throws CrudException {
		crud.load(model);
	}
	
	@Post("")
	@Consumes(value = "application/json")
	public void add(T model) throws CrudException {
		edit(model);
	}
	
	@Put("/{model.id}")
	@Consumes(value = "application/json")
	public void edit(T model) throws CrudException {
		crud.save(model);
	}
	
	@Delete("/{model.id}")
	public void remove(T model) throws CrudException {
		crud.delete(model);
	}

	protected Crud crud() {
		return crud;
	}

}