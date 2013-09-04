package org.dtelaroli.vplus.core.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for to help in tests 
 * @author denilson
 */
@Entity
public class NewEntity extends Model {
	
	private static final long serialVersionUID = 3180713002283133563L;

	@NotEmpty
	protected String name;
	
	public NewEntity() {
		super();
	}
	
	public NewEntity(Long id, String name) {
		super();
		super.id = id;
		this.name = name;
	}
	
	public String name() {
		return name;
	}

}