package org.vplus.core.persistence;

import javax.persistence.Entity;

import org.vplus.core.generics.Model;

/**
 * Class for to help in tests 
 * @author denilson
 */
@Entity
public class MyEntity extends Model {
	
	private static final long serialVersionUID = 3180713002283133563L;

	String name;
	
	public MyEntity() {}
	
	public MyEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getLabel() {
		return name;
	}
}