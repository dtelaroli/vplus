package org.vplus.core.generics;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for to help in tests 
 * @author denilson
 */
@Entity
public class MyEntity extends ModelPlus {
	
	private static final long serialVersionUID = 3180713002283133563L;

	@NotEmpty
	protected String name;
	
	public MyEntity() {
		super();
	}
	
	public MyEntity(Long id) {
		this.id = id;
	}
	
	public MyEntity(String name) {
		this.name = name;
	}
	
	public MyEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getLabel() {
		return name;
	}
}