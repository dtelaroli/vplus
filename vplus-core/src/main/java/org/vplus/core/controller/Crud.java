package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;

public interface Crud {

	Crud of(Class<? extends Model> clazz);

	Class<? extends Model> type();

	boolean isAsc();
	
	Crud asc() throws CrudException;

	Crud desc() throws CrudException;

	Crud withDirection(Direction order) throws CrudException;
	
	Crud withOrder(String order);

	String order();

	Result result();
	
	Crud withLimit(Integer limit);

	Integer limit();

	void list() throws CrudException;
	
	void load(Model model) throws CrudException;

	void save(Model model) throws CrudException;

	void delete(Model model) throws CrudException;

	Crud withStatus(Status status);

	Status status();

	<T extends Action> Action use(Class<T> action);

}