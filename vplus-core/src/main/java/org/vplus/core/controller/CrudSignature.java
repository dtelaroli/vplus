package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

public interface CrudSignature<T extends Model> {
	
	@Get("/")
	public void all() throws CrudException;
	
	@Get("/{model.id}")
	public void get(T model) throws CrudException;
	
	@Post("/")
	@Consumes(value = "application/json")
	public void add(T model) throws CrudException;
	
	@Put("/{model.id}")
	@Consumes(value = "application/json")
	public void edit(T model) throws CrudException;
	
	@Delete("/{model.id}")
	@Consumes(value = "application/json")
	public void remove(T model) throws CrudException;
	
}