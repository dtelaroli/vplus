package org.vplus.web;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.vplus.core.generics.Model;

@Entity
public class NewModel extends Model {

	private static final long serialVersionUID = -7490419469857568428L;
	
	@NotNull(message ="{description} {javax.validation.constraints.NotNull.message}")
	private String description;
	
	public NewModel() {
	}
	
	public NewModel(Long id, String description) {
		super(id);
		this.description = description;
	}
	
	@Override
	public String getLabel() {
		return description;
	}

}
