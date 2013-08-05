package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.model.Model;
import org.vplus.core.model.Status;
import org.vplus.core.persistence.Direction;

import br.com.caelum.vraptor.Result;

public interface CrudController {

	CrudController of(Class<? extends Model> clazz);

	Class<? extends Model> type();

	boolean isAsc();
	
	CrudController asc() throws CrudException;

	CrudController desc() throws CrudException;

	CrudController withDirection(Direction order) throws CrudException;
	
	CrudController withOrder(String order);

	String order();

	Result result();
	
	CrudController withLimit(Integer limit);

	Integer limit();

	void list() throws CrudException;
	
	void load(Model model) throws CrudException;

	void save(Model model) throws CrudException;

	void delete(Model model) throws CrudException;

	CrudController withStatus(Status status);

	Status status();

	<T extends Action> Action use(Class<T> action);

}