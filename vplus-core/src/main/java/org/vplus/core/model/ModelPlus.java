package org.vplus.core.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@FilterDef(name = "statusFilter", parameters = { @ParamDef(name = "status", type = "integer") })
@Filter(name = "statusFilter", condition = "status = :status")
public abstract class ModelPlus extends Model {

	private static final long serialVersionUID = 5183252859183741057L;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modified;

	@NotNull
	@Column(length = 1)
	@Enumerated
	private Status status;
	
	public ModelPlus() {
		super();
		status = Status.Active;
	}

	public ModelPlus(Long id) {
		super(id);
		status = Status.Active;
	}

	public Status status() {
		return status;
	}

	public ModelPlus withStatus(Status status) {
		this.status = status;
		return this;
	}

	public Calendar createdAt() {
		return created;
	}

	public Calendar modifiedAt() {
		return modified;
	}
	
	@PrePersist
	public void beforeInsert() {
		created = Calendar.getInstance();
		beforeUpdate();
	}
	
	@PreUpdate
	public void beforeUpdate() {
		modified = Calendar.getInstance();
	}

	@Override
	public String toString() {
		return "ModelPlus [id=" + id + ", created=" + created + ", modified="
				+ modified + ", status=" + status + "]";
	}
	
}