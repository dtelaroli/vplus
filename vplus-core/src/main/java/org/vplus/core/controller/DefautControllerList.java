package org.vplus.core.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.vplus.core.persistence.Persistences.list;

import java.util.List;

import org.vplus.core.exeption.VPlusException;
import org.vplus.core.generics.Model;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefautControllerList implements ControllerList {

	private ControllerListExecute listExecute;

	public DefautControllerList(ControllerListExecute listExecute) {
		this.listExecute = listExecute;
	}
	
	@Override
	public ControllerListExecute of(Class<? extends Model> clazz) throws VPlusException {
		return listExecute.of(clazz);
	}

	@Component
	public static class ControllerListExecute {
		private Persistence persistence;
		private Result result;
		private Class<? extends Model> clazz;
		
		protected ControllerListExecute(Result result, Persistence persistence) {
			this.result = result;
			this.persistence = persistence;
		}
		
		private ControllerListExecute of(Class<? extends Model> clazz) {
			this.clazz = clazz;
			return this;			
		}
		
		public void serialize() throws VPlusException {
			result.use(json()).from(getList()).serialize();			
		}

		protected List<Model> getList() throws VPlusException {
			return persistence.use(list()).of(clazz).find();
		}
	}

}