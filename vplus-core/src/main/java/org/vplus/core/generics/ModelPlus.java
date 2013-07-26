package org.vplus.core.generics;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@MappedSuperclass
@FilterDef(name = "statusFilter", parameters = { @ParamDef(name = "status", type = "integer") })
@Filter(name = "statusFilter", condition = "status = :status")
public abstract class ModelPlus extends Model {

	private static final long serialVersionUID = 5183252859183741057L;

	public ModelPlus() {
		super();
		created = new DateTime();
		modified = new DateTime();
		status = Status.ACTIVE;
	}

	@Column(updatable = false)
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime created;

	@Column
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime modified;

	@NotNull
	@Column(length = 1)
	@Enumerated
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public DateTime getCreated() {
		return created;
	}

	public DateTime getModified() {
		return modified;
	}

}