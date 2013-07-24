package org.vplus.web;

import static br.com.caelum.vraptor.view.Results.json;
import static org.vplus.core.persistence.Persistences.list;
import static org.vplus.core.persistence.Persistences.save;

import org.vplus.core.persistence.MyEntity;
import org.vplus.core.persistence.Persistence;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MyController {

	private Result result;
	private Persistence persistence;

	public MyController(Result result, Persistence persistence) {
		this.result = result;
		this.persistence = persistence;
	}

	@Get("/my")
	public void index() {
		persistence.use(save()).of(MyEntity.class).persist(new MyEntity());
		
		result.use(json()).from(
			persistence.use(list()).of(MyEntity.class).find()
		).serialize();
	}
}
