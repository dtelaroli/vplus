package org.vplus.core.controller;

import org.vplus.core.exception.CrudException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Result;

public interface CrudController {

	CrudController of(Class<? extends Model> clazz);

	Class<? extends Model> type();

	void list() throws CrudException;
	
	CrudController asc() throws CrudException;

	boolean isAsc();

	CrudController desc() throws CrudException;

	CrudController withDirection(Order order) throws CrudException;
	
	CrudController withOrder(String order);

	String order();

	Result result();

	void actionExecute() throws CrudException;

	void load(Model model) throws CrudException;

	void save(Model model) throws CrudException;

	void delete(Model model) throws CrudException;

	CrudController withLimit(Integer limit);

	Integer limit();

}