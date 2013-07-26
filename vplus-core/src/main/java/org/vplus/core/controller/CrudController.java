package org.vplus.core.controller;

import org.vplus.core.exception.VPlusException;
import org.vplus.core.generics.Model;

import br.com.caelum.vraptor.Result;

public interface CrudController {

	CrudController of(Class<? extends Model> clazz);

	Class<? extends Model> type();

	void list() throws VPlusException;

	Result result();

	void actionExecute() throws VPlusException;

	void load(Model model) throws VPlusException;

	void save(Model model) throws VPlusException;

	void delete(Model model) throws VPlusException;

}
