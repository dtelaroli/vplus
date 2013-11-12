package br.com.flexait.controllers;

import java.util.List;

import br.com.flexait.models.Prod;
import br.com.flexait.repositories.ProdRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class ProdController {

	private final Result result;
	private final ProdRepository repository;
	
	private final Validator validator;
	
	public ProdController(Result result, ProdRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/prods")
	public List<Prod> index() {
		return repository.findAll();
	}
	
	@Post("/prods")
	public void create(Prod prod) {
		validator.validate(prod);
		validator.onErrorUsePageOf(this).newProd();
		repository.create(prod);
		result.redirectTo(this).index();
	}
	
	@Get("/prods/new")
	public Prod newProd() {
		return new Prod();
	}
	
	@Put("/prods")
	public void update(Prod prod) {
		validator.validate(prod);
		validator.onErrorUsePageOf(this).edit(prod);
		repository.update(prod);
		result.redirectTo(this).index();
	}
	
	@Get("/prods/{prod.id}/edit")
	public Prod edit(Prod prod) {
		
		return repository.find(prod.getId());
	}

	@Get("/prods/{prod.id}")
	public Prod show(Prod prod) {
		return repository.find(prod.getId());
	}

	@Delete("/prods/{prod.id}")
	public void destroy(Prod prod) {
		repository.destroy(repository.find(prod.getId()));
		result.redirectTo(this).index();  
	}
}