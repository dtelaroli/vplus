package org.vplus.core.crud;

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
	
	@Override
	public String getLabel() {
		return name;
	}
}