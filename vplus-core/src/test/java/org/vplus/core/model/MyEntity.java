package org.vplus.core.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.vplus.core.model.ModelPlus;

/**
 * Class for to help in tests
 *
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
        super(id);
    }

    public MyEntity(String name) {
        this.name = name;
    }

    public MyEntity(Long id, String name) {
        super(id);
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
        return new String[]{"orderField"};
    }
}