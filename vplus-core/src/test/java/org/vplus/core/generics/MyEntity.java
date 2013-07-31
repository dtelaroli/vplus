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
		this(id, null);
	}
	
	public MyEntity(String name) {
		this(null, name);
	}
	
	public MyEntity(Long id, String name) {
		super();
		super.id = id;
		this.name = name;
	}
	
	public String name() {
		return name;
	}

	@Override
	public String[] includes() {
		return new String[]{"id", "name"};
	}
	
	@Override
	public String[] excludes() {
		return new String[]{"name"};
	}
}