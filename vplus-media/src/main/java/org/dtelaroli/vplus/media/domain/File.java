package org.dtelaroli.vplus.media.domain;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dtelaroli.vplus.core.model.ModelPlus;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.ByteStreams;

@Entity
public class File extends ModelPlus {

	private static final long serialVersionUID = 1503777731816339258L;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String ext;
	
	@NotEmpty
	private String type;
	
	@NotNull
	private Long size;
	
	@NotEmpty
	private String hash;
	
	@NotNull
	private byte[] binary;
	
	@Transient
	private UploadedFile uploadedFile;
	
	@PrePersist
	@PreUpdate
	public void setByteArray() throws IOException {
		name = uploadedFile.getFileName();
		size = uploadedFile.getSize();
		binary = ByteStreams.toByteArray(uploadedFile.getFile());
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	protected String getExt() {
		return ext;
	}

	protected void setExt(String ext) {
		this.ext = ext;
	}

	protected String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	protected Long getSize() {
		return size;
	}

	protected void setSize(Long size) {
		this.size = size;
	}

	protected String getHash() {
		return hash;
	}

	protected void setHash(String hash) {
		this.hash = hash;
	}

	protected byte[] getBinary() {
		return binary;
	}

	protected void setBinary(byte[] binary) {
		this.binary = binary;
	}
	
}