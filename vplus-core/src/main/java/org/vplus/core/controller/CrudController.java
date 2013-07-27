package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Result;

public interface CrudController {

	CrudController of(Class<? extends Model> clazz);

	Class<? extends Model> type();

	void list() throws CrudException;
	
	CrudController list(String order);

	Result result();

	void actionExecute() throws CrudException;

	void load(Model model) throws CrudException;

	void save(Model model) throws CrudException;

	void delete(Model model) throws CrudException;

	void asc() throws CrudException;

	boolean isAsc();

	void desc() throws CrudException;

	CrudController withDirection(Order order);

	String order();

}